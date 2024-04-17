package com.example.demo.common.exception;

import com.example.demo.security.hadnler.AuthenticationExceptionHandler;
import org.springframework.security.core.AuthenticationException;

public class MailCodeExpiredException extends AuthenticationException {

    public MailCodeExpiredException(String msg) {
        super(msg);
    }
}
