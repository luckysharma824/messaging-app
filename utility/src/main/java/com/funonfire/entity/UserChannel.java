package com.funonfire.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Table
public class UserChannel {
    @PrimaryKey
    private String id;
    private String userId;
    private String channelId;
}
