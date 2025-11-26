package com.but.rebloom.domain.channel.exception;

import com.but.rebloom.global.exception.EntityException;

public class InsufficientPointException extends EntityException {
    public InsufficientPointException(String message) {
        super(message);
    }
}
