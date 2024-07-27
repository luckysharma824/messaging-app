package com.funonfire.entity;

import com.funonfire.enums.ChannelTypeEnum;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Table
public class Channel {
    @PrimaryKey
    private String id;
    private String name;
    private ChannelTypeEnum channelType;
}
