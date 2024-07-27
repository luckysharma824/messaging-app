package com.funonfire.service;

import com.funonfire.dto.RestResponseDto;
import com.funonfire.dto.SearchRequestDto;
import com.funonfire.entity.Channel;
import com.funonfire.enums.ChannelTypeEnum;

import java.util.List;

public interface ChatService {
    RestResponseDto<List<Channel>> getAll(String userId, SearchRequestDto searchRequest);

    Channel createChannel(String toUserId, String fromUserId, ChannelTypeEnum channelType);

}
