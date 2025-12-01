package com.but.rebloom.domain.reaction.exception;

import com.but.rebloom.global.exception.NotFoundException;

public class HeartNotFoundException extends NotFoundException {
    public HeartNotFoundException(String message) {
        super(message);
    }
}
