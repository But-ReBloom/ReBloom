package com.but.rebloom.common.exception;

public class TokenExpiredException extends AuthenticationException {
    public TokenExpiredException(String message) {
        super(message);
    }
}
