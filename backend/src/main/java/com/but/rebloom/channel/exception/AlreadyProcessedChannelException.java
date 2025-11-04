package com.but.rebloom.channel.exception;

import com.but.rebloom.common.exception.AlreadyUsingException;

public class AlreadyProcessedChannelException extends AlreadyUsingException {
    public AlreadyProcessedChannelException(String message) {
        super(message);
    }
}
