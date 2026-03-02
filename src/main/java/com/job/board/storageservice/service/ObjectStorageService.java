package com.job.board.storageservice.service;

import com.job.board.storageservice.model.dto.FileMetadataUploadDto;

import java.io.InputStream;

public interface ObjectStorageService {

    void save(FileMetadataUploadDto dto, InputStream inputStream);

    InputStream findByBucketAndStoredName(String bucket, String storedName);

    void delete(String bucket, String storedName);
}
