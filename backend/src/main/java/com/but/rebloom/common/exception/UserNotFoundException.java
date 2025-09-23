package com.but.rebloom.common.exception;

public class UserNotFoundException extends AuthenticationException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
