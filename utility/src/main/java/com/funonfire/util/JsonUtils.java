package com.funonfire.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.funonfire.exception.ServiceException;

public class JsonUtils {

    private JsonUtils() {}

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJson(Object sourceObject) {
        try {
            return OBJECT_MAPPER.writeValueAsString(sourceObject);
        } catch (JsonProcessingException jpe) {
            throw new ServiceException("EC-MAP_ERR", jpe.getMessage());
        }
    }

    public static  <T> T fromJson(String content, Class<T> targetClass) {
        try {
            return OBJECT_MAPPER.readValue(content, targetClass);
        } catch (JsonProcessingException jpe) {
            throw new ServiceException("EC-MAP_ERR", jpe.getMessage());
        }
    }

}
