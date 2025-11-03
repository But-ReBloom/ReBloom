package com.but.rebloom.channel.exception;

import com.but.rebloom.common.exception.NotFoundException;

public class ChannelNotFoundException extends NotFoundException {
    public ChannelNotFoundException(String message) {
        super(message);
    }
}
