package com.but.rebloom.domain.review.exception;

import com.but.rebloom.global.exception.NotFoundException;

public class ReviewNotFoundException extends NotFoundException {
    public ReviewNotFoundException(String message) {
        super(message);
    }
}
