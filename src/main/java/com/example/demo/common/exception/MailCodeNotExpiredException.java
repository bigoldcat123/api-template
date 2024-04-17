package com.example.demo.common.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;

@Log4j2
public class MailCodeNotExpiredException extends AuthenticationException {
    public MailCodeNotExpiredException(String message) {
        super(message);
    }
}
