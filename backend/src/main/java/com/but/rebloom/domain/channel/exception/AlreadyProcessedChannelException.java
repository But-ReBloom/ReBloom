package com.but.rebloom.domain.channel.exception;

import com.but.rebloom.global.exception.AlreadyUsingException;

public class AlreadyProcessedChannelException extends AlreadyUsingException {
    public AlreadyProcessedChannelException(String message) {
        super(message);
    }
}
