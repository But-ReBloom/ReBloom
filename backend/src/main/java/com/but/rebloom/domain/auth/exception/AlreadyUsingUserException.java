package com.but.rebloom.domain.auth.exception;

import com.but.rebloom.global.exception.AlreadyUsingException;

public class AlreadyUsingUserException extends AlreadyUsingException {
    public AlreadyUsingUserException(String message) {
        super(message);
    }
}
