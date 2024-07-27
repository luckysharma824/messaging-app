package com.funonfire.service;

import com.datastax.oss.driver.api.core.cql.PagingState;
import com.funonfire.constants.AppConstants;
import com.funonfire.dto.RestResponseDto;
import com.funonfire.dto.SearchRequestDto;
import com.funonfire.entity.Channel;
import com.funonfire.entity.UserChannel;
import com.funonfire.enums.ChannelTypeEnum;
import com.funonfire.repository.ChannelRepository;
import com.funonfire.repository.UserChannelRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChannelRepository channelRepository;
    private final UserChannelRepository userChannelRepository;

    public RestResponseDto<List<Channel>> getAll(String userId, SearchRequestDto searchRequest) {
        RestResponseDto<List<Channel>> restResponse = new RestResponseDto<>();
        String cursorMark = searchRequest.getProperties().get(AppConstants.CURSOR_MARK_CONSTANT);
        Pageable pageable = CassandraPageRequest.of(
                PageRequest.of(searchRequest.getPageNum(), searchRequest.getPageSize()),
                AppConstants.DEFAULT_CURSOR_MARK.equals(cursorMark) ?
                        null : PagingState.fromString(cursorMark).getRawPagingState()
        );
        Slice<Channel> channels = channelRepository.findByUserId(userId, pageable);
        String pagingState = channels.isLast() ? AppConstants.DEFAULT_CURSOR_MARK :
                Objects.requireNonNull(((CassandraPageRequest) channels.getPageable()).getPagingState()).toString();

        restResponse.getProperties().put(AppConstants.CURSOR_MARK_CONSTANT, pagingState);
        restResponse.setData(channels.getContent());
        return restResponse;
    }

    @Override
    public Channel createChannel(String fromUserId, String toUserId, ChannelTypeEnum channelType) {
        String uuid = UUID.randomUUID().toString();
        Channel channel = new Channel();
        channel.setChannelType(channelType);
        channel.setName(uuid);
        channel.setId(uuid);
        channelRepository.save(channel);

        UserChannel userChannel1 = new UserChannel();
        userChannel1.setChannelId(channel.getId());
        userChannel1.setUserId(toUserId);
        userChannel1.setId(UUID.randomUUID().toString());

        UserChannel userChannel = new UserChannel();
        userChannel.setChannelId(channel.getId());
        userChannel.setUserId(fromUserId);
        userChannel.setId(UUID.randomUUID().toString());

        userChannelRepository.saveAll(Arrays.asList(userChannel1, userChannel));
        return channel;
    }

}
