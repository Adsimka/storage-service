package com.job.board.storageservice.exception;

public class FileMetadataNotFoundException extends RuntimeException {

    public FileMetadataNotFoundException(String message) {
        super(message);
    }
}
