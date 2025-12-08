package com.but.rebloom.domain.channel.exception;

import com.but.rebloom.global.exception.EntityException;

public class InsufficientTierPointException extends EntityException {
    public InsufficientTierPointException(String message) {
        super(message);
    }
}
