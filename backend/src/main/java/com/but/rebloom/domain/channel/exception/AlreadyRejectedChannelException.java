package com.but.rebloom.domain.channel.exception;

public class AlreadyRejectedChannelException extends RuntimeException {
    public AlreadyRejectedChannelException(String message) {
        super(message);
    }
}
