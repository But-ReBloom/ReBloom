package com.but.rebloom.auth.exception;

import com.but.rebloom.common.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
