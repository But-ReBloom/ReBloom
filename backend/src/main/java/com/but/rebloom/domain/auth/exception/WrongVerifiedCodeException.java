package com.but.rebloom.domain.auth.exception;

import com.but.rebloom.global.exception.AuthenticationException;

public class WrongVerifiedCodeException extends AuthenticationException {
    public WrongVerifiedCodeException(String message) {
        super(message);
    }
}
