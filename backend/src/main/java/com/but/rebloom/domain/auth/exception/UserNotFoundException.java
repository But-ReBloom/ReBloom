package com.but.rebloom.domain.auth.exception;

import com.but.rebloom.global.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
