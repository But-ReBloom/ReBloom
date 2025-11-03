package com.but.rebloom.channel.exception;

import com.but.rebloom.common.exception.AlreadyUsingException;

public class AlreadyExistException extends AlreadyUsingException {
    public AlreadyExistException(String message) {
        super(message);
    }
}
