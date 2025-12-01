package com.but.rebloom.domain.auth.exception;

import com.but.rebloom.global.exception.AuthenticationException;

public class TokenExpiredException extends AuthenticationException {
    public TokenExpiredException(String message) {
        super(message);
    }
}
