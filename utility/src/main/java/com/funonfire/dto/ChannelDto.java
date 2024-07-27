package com.funonfire.dto;

import com.funonfire.enums.ChannelTypeEnum;
import lombok.Data;

@Data
public class ChannelDto {
    private String id;
    private String name;
    private ChannelTypeEnum channelType;

}
