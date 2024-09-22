package com.czh.api.common.exception;

import org.springframework.security.core.AuthenticationException;

public class MailCodeExpiredException extends AuthenticationException {

    public MailCodeExpiredException(String msg) {
        super(msg);
    }
}
