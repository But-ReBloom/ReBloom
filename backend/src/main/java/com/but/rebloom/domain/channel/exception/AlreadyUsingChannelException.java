package com.but.rebloom.domain.channel.exception;

import com.but.rebloom.global.exception.AlreadyUsingException;

public class AlreadyUsingChannelException extends AlreadyUsingException {
    public AlreadyUsingChannelException(String message) {
        super(message);
    }
}
