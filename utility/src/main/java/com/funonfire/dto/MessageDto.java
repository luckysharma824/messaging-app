package com.funonfire.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.funonfire.enums.ChannelTypeEnum;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {
    private String id;
    private String channelId;
    private ChannelTypeEnum channelType;
    private String toUserId;
    private String type;
    private String content;
    private Timestamp sendAt;
    private Timestamp deliveredAt;
    private Timestamp seenAt;
}
