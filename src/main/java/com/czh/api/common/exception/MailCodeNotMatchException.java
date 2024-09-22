package com.czh.api.common.exception;

import org.springframework.security.core.AuthenticationException;

public class MailCodeNotMatchException extends AuthenticationException {
    public MailCodeNotMatchException(String message) {
        super(message);
    }
}
