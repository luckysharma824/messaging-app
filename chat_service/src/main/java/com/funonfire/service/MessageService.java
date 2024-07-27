package com.funonfire.service;

import com.funonfire.dto.MessageDto;
import com.funonfire.dto.RestResponseDto;
import com.funonfire.dto.SearchRequestDto;
import com.funonfire.dto.inbound.MessageRequestDto;
import com.funonfire.entity.Message;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface MessageService {
    RestResponseDto<List<Message>> getMessages(String userId, String channelId, SearchRequestDto searchRequest);
    void sendMessage(MessageRequestDto request);
}
