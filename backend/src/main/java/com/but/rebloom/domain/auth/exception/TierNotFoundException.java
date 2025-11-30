package com.but.rebloom.domain.auth.exception;

import com.but.rebloom.global.exception.NotFoundException;

public class TierNotFoundException extends NotFoundException {
    public TierNotFoundException(String message) {
        super(message);
    }
}
