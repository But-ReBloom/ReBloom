package com.but.rebloom.domain.hobby.exception;

import com.but.rebloom.global.exception.AlreadyUsingException;

public class AlreadyUsingActivityException extends AlreadyUsingException {
    public AlreadyUsingActivityException(String message) {
        super(message);
    }
}
