package com.bit.blog.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParameterException extends RuntimeException {

    private String code;

    public ParameterException(String message) {
        super("客户端错误："+message);
        code = "400";
    }

    public ParameterException(String message, Throwable cause) {
        super("客户端错误："+message, cause);
        code = "400";
    }
}
