package com.but.rebloom.domain.channel.exception;

import com.but.rebloom.global.exception.NotFoundException;

public class ChannelNotFoundException extends NotFoundException {
    public ChannelNotFoundException(String message) {
        super(message);
    }
}
