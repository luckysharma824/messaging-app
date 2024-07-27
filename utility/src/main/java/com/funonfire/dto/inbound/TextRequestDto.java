package com.funonfire.dto.inbound;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TextRequestDto extends MessageRequestDto {
    private TextDto text;
}
