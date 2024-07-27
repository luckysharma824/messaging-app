package com.funonfire.dto.inbound;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.funonfire.enums.ChannelTypeEnum;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextRequestDto.class, name = "text")
})
public class MessageRequestDto {
    private String channelId;
    private ChannelTypeEnum channelType;
    private String toUserId;
    private String type;
}
