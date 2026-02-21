package com.job.board.storageservice.exception.handler;

import com.job.board.storageservice.exception.FileMetadataNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(FileMetadataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ProblemDetail> handleFileMetadataNotFoundException(FileMetadataNotFoundException e) {
        log.error("Product not found: %s".formatted(e.getMessage()), e);
        ProblemDetail response = buildProblemDetail(
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private ProblemDetail buildProblemDetail(HttpStatus status, String message) {
        return ProblemDetail.forStatusAndDetail(status, message);
    }
}
