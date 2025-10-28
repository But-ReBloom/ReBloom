package com.but.rebloom.auth.exception;

import com.but.rebloom.common.exception.AlreadyUsingException;

public class AlreadyUsingUserException extends AlreadyUsingException {
    public AlreadyUsingUserException(String message) {
        super(message);
    }
}
