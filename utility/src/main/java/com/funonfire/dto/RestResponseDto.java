package com.funonfire.dto;

import lombok.Data;

import java.util.Map;

@Data
public class RestResponseDto <T>{
    private T data;
    Map<String, String> properties;
}
