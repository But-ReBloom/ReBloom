package com.but.rebloom.hobby.exception;

import com.but.rebloom.common.exception.AlreadyUsingException;

public class AlreadyUsingActivityException extends AlreadyUsingException {
    public AlreadyUsingActivityException(String message) {
        super(message);
    }
}
