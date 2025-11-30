package com.but.rebloom.domain.channel.exception;

import com.but.rebloom.global.exception.AlreadyUsingException;

public class AlreadyUsingUserChannelException extends AlreadyUsingException {
    public AlreadyUsingUserChannelException(String message) {
        super(message);
    }
}
