package com.but.rebloom.common.exception;

public class NotFoundException extends AuthenticationException {
    public NotFoundException(String message) {
        super(message);
    }
}
