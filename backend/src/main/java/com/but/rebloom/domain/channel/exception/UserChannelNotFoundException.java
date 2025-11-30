package com.but.rebloom.domain.channel.exception;

import com.but.rebloom.global.exception.NotFoundException;

public class UserChannelNotFoundException extends NotFoundException {
    public UserChannelNotFoundException(String message) {
        super(message);
    }
}
