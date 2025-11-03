package com.but.rebloom.channel.exception;

import com.but.rebloom.common.exception.EntityException;

public class InsufficientPointException extends EntityException {
    public InsufficientPointException(String message) {
        super(message);
    }
}
