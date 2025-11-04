package com.but.rebloom.channel.exception;

import com.but.rebloom.common.exception.NotFoundException;

public class CommentNotFoundException extends NotFoundException {
    public CommentNotFoundException(String message) {
        super(message);
    }
}
