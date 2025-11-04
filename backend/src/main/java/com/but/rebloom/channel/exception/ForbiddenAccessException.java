package com.but.rebloom.channel.exception;

import com.but.rebloom.common.exception.AuthenticationException;

public class ForbiddenAccessException extends AuthenticationException {
    public ForbiddenAccessException(String message) {
        super(message);
    }
}
