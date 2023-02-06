package com.ohble.global.exception;


public class CustomException extends BaseException {
    public CustomException(ExceptionType exceptionType) {
        super(exceptionType);
    }
}
