package com.but.rebloom.domain.channel.exception;

import com.but.rebloom.global.exception.NotFoundException;

public class PostNotFoundException extends NotFoundException {
    public PostNotFoundException(String message) {
        super(message);
    }
}
