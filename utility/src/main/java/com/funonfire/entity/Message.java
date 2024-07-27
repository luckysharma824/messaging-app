package com.funonfire.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.funonfire.enums.ChannelTypeEnum;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Table
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    @PrimaryKey
    private String id;
    private String channelId;
    private ChannelTypeEnum channelType;
    private String toUserId;
    private String type;
    private String content;
    private Long sendAt;
    private Long deliveredAt;
    private Long seenAt;
}
