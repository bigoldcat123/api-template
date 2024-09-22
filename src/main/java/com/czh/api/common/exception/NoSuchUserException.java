package com.czh.api.common.exception;

import org.springframework.security.core.AuthenticationException;

public class NoSuchUserException extends AuthenticationException {

    public NoSuchUserException(String msg) {
        super(msg);
    }
}
