package com.but.rebloom.auth.exception;

import com.but.rebloom.common.exception.AuthenticationException;

public class TokenExpiredException extends AuthenticationException {
    public TokenExpiredException(String message) {
        super(message);
    }
}
