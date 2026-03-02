package com.job.board.storageservice.service.impl;

import com.job.board.storageservice.exception.FileReceivingException;
import com.job.board.storageservice.exception.FileRemoveException;
import com.job.board.storageservice.exception.FileUploadException;
import com.job.board.storageservice.model.dto.FileMetadataUploadDto;
import com.job.board.storageservice.service.ObjectStorageService;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ObjectStorageServiceImpl implements ObjectStorageService {

    private final MinioClient minioClient;

    public void save(FileMetadataUploadDto dto, InputStream inputStream) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(dto.bucket())
                            .object(dto.storedName())
                            .stream(inputStream, dto.sizeBytes(), -1)
                            .contentType(dto.contentType())
                            .build()
            );
            log.info("File uploaded: bucket={}, object={}", dto.bucket(), dto.storedName());
        } catch (Exception exception) {
            String errorMessage = "Failed to upload file: bucket=%s, object=%s".formatted(dto.bucket(), dto.storedName());
            log.error(errorMessage, exception);
            throw new FileUploadException(errorMessage, exception);
        }
    }

    public InputStream findByBucketAndStoredName(String bucket, String storedName) {
        try {
             log.info("File retrieved: bucket={}, object={}", bucket, storedName);
             return minioClient.getObject(
                     GetObjectArgs.builder()
                             .bucket(bucket)
                             .object(storedName)
                             .build()
             );
        } catch (Exception exception) {
            String errorMessage = "Error receiving file: bucket=%s, object=%s".formatted(bucket, storedName);
            log.error(errorMessage, exception);
            throw new FileReceivingException(errorMessage, exception);
        }
    }

    public void delete(String bucket, String storedName) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .object(storedName)
                            .bucket(bucket)
                            .build()
            );
            log.info("File deleted: bucket={}, object={}", bucket, storedName);
        } catch (Exception exception) {
            String errorMessage = "Failed remove object: bucket=%s, object=%s".formatted(bucket, storedName);
            log.error(errorMessage, exception);
            throw new FileRemoveException(errorMessage, exception);
        }
    }
}
