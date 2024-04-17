package com.example.demo.common.exception;

import org.springframework.security.core.AuthenticationException;

public class MailCodeNotMatchException extends AuthenticationException {
    public MailCodeNotMatchException(String message) {
        super(message);
    }
}
