package com.funonfire.service;

import com.datastax.oss.driver.api.core.cql.PagingState;
import com.funonfire.constants.AppConstants;
import com.funonfire.dto.RestResponseDto;
import com.funonfire.dto.SearchRequestDto;
import com.funonfire.dto.inbound.MessageRequestDto;
import com.funonfire.dto.inbound.TextRequestDto;
import com.funonfire.entity.Message;
import com.funonfire.enums.ChannelTypeEnum;
import com.funonfire.repository.MessageRepository;
import com.funonfire.util.JsonUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.*;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public RestResponseDto<List<Message>> getMessages(String userId, String channelId, SearchRequestDto searchRequest) {
        /*Pageable pageable = CassandraPageRequest.of(searchRequest.getPageNum(), searchRequest.getPageSize());*/ //TODO we can do this as well
        RestResponseDto<List<Message>> response = new RestResponseDto<>();
        response.setProperties(new HashMap<>());
        ByteBuffer cursorMark = searchRequest.getProperties() == null
                || searchRequest.getProperties().get(AppConstants.CURSOR_MARK_CONSTANT) == null ?
                null : PagingState.fromString(searchRequest.getProperties().get(AppConstants.CURSOR_MARK_CONSTANT)).getRawPagingState();
        Pageable pageable = CassandraPageRequest.of(
                PageRequest.of(searchRequest.getPageNum(), searchRequest.getPageSize()),
                cursorMark
        );
        Slice<Message> messages = messageRepository.findByChannelId(channelId, pageable);
        if(messages.isLast()) {
            response.getProperties().put(AppConstants.CURSOR_MARK_CONSTANT, AppConstants.DEFAULT_CURSOR_MARK);
        } else {
            response.getProperties().put(
                    AppConstants.CURSOR_MARK_CONSTANT,
                    Objects.requireNonNull(((CassandraPageRequest) messages.getPageable()).getPagingState()).toString()
            );
        }
        response.setData(messages.getContent());
        return response;
    }

    public void sendMessage(MessageRequestDto request) {
        Message message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setChannelId(request.getChannelId());
        message.setChannelType(request.getChannelType());
        message.setToUserId(request.getToUserId());
        message.setType(request.getType());
        message.setSendAt(Instant.now().getEpochSecond());

        if (request instanceof TextRequestDto requestDto) {
            String content = JsonUtils.toJson(requestDto.getText());
            message.setContent(content);
        }

        messageRepository.save(message);
    }

}
