package com.funonfire.dto;

import lombok.Data;

import java.util.Map;

@Data
public class SearchRequestDto {
    private Integer pageNum;
    private Integer pageSize;
    private String sortKey;
    private Map<String, String> properties;
}
