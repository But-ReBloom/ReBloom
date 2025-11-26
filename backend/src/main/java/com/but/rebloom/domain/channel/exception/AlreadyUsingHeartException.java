package com.but.rebloom.domain.channel.exception;

import com.but.rebloom.global.exception.AlreadyUsingException;

public class AlreadyUsingHeartException extends AlreadyUsingException {
    public AlreadyUsingHeartException(String message) {
        super(message);
    }
}
