package com.but.rebloom.domain.reaction.exception;

import com.but.rebloom.global.exception.AlreadyUsingException;

public class AlreadyUsingHeartException extends AlreadyUsingException {
    public AlreadyUsingHeartException(String message) {
        super(message);
    }
}
