package com.funonfire.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    private ResponseHandler(){}

    public static ResponseEntity<Object> response(Object data, String message, boolean isSuccess, HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("message", message);
        response.put("isSuccess", isSuccess);
        response.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> response(String message, boolean isSuccess, HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("isSuccess", isSuccess);
        response.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(response, httpStatus);
    }
}
