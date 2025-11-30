package com.but.rebloom.global.exception;

public class NoAuthorityException extends AuthenticationException {
    public NoAuthorityException(String message) {
        super(message);
    }
}
