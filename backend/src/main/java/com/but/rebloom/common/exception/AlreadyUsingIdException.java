package com.but.rebloom.common.exception;

public class AlreadyUsingIdException extends AuthenticationException {
    public AlreadyUsingIdException(String message) {
        super(message);
    }
}
