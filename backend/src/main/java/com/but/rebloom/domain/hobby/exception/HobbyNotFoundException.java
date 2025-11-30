package com.but.rebloom.domain.hobby.exception;

public class HobbyNotFoundException extends RuntimeException {
    public HobbyNotFoundException(String message) {
        super(message);
    }
}
