package com.funonfire.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServiceException extends RuntimeException {

    private String errCode;

    public ServiceException() {
        super();
    }

    public ServiceException(String errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

}
