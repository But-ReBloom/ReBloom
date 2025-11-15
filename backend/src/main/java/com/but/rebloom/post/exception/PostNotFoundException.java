package com.but.rebloom.post.exception;

import com.but.rebloom.common.exception.NotFoundException;

public class PostNotFoundException extends NotFoundException {
    public PostNotFoundException(String message) {
        super(message);
    }
}
