package com.ohble.global.exception;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ExceptionInformation {

    private String exception;

    private String message;

    private int status;

    private String error;
}
