package com.bit.blog.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {

    private String code;

    public BusinessException(String message) {
        super("业务异常："+message);
        code = "401";
    }

    public BusinessException(String message, Throwable cause) {
        super("业务异常："+message, cause);
        code = "401";
    }
}
