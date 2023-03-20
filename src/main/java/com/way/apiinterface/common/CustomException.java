package com.way.apiinterface.common;

/**
 * 自定义的异常
 * @author Way
 */
public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
}
