package com.but.rebloom.channel.exception;

import com.but.rebloom.common.exception.AlreadyUsingException;

public class AlreadyUsingHeartException extends AlreadyUsingException {
    public AlreadyUsingHeartException(String message) {
        super(message);
    }
}
