package com.but.rebloom.domain.channel.exception;

import com.but.rebloom.global.exception.AuthenticationException;

public class NoAuthorityException extends AuthenticationException {
    public NoAuthorityException(String message) {
        super(message);
    }
}
