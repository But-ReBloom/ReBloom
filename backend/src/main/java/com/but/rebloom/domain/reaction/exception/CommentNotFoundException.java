package com.but.rebloom.domain.reaction.exception;

import com.but.rebloom.global.exception.NotFoundException;

public class CommentNotFoundException extends NotFoundException {
    public CommentNotFoundException(String message) {
        super(message);
    }
}
