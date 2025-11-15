package com.but.rebloom.auth.exception;

import com.but.rebloom.common.exception.NotFoundException;

public class TierNotFoundException extends NotFoundException {
    public TierNotFoundException(String message) {
        super(message);
    }
}
