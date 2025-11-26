package com.but.rebloom.global.exception;

public class AlreadyUsingException extends AuthenticationException {
    public AlreadyUsingException(String message) {
        super(message);
    }
}
