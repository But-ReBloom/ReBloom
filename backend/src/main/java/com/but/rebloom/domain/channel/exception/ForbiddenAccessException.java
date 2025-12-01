package com.but.rebloom.domain.channel.exception;

import com.but.rebloom.global.exception.AuthenticationException;

public class ForbiddenAccessException extends AuthenticationException {
    public ForbiddenAccessException(String message) {
        super(message);
    }
}
