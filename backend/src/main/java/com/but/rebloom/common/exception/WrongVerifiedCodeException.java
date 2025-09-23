package com.but.rebloom.common.exception;

public class WrongVerifiedCodeException extends AuthenticationException {
    public WrongVerifiedCodeException(String message) {
        super(message);
    }
}
