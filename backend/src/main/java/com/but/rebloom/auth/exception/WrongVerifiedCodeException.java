package com.but.rebloom.auth.exception;

import com.but.rebloom.common.exception.AuthenticationException;

public class WrongVerifiedCodeException extends AuthenticationException {
    public WrongVerifiedCodeException(String message) {
        super(message);
    }
}
