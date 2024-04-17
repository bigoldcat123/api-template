package com.example.demo.common.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MyCommonException extends RuntimeException{
    public MyCommonException(String message) {
        super(message);
        log.error(message);
    }
}
