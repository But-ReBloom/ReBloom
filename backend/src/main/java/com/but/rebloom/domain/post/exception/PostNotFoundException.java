package com.but.rebloom.domain.post.exception;

import com.but.rebloom.global.exception.NotFoundException;

public class PostNotFoundException extends NotFoundException {
    public PostNotFoundException(String message) {
        super(message);
    }
}
