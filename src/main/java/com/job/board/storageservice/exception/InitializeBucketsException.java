package com.job.board.storageservice.exception;

public class InitializeBucketsException extends RuntimeException {

    public InitializeBucketsException(String message, Exception exception) {
        super(message, exception);
    }
}
