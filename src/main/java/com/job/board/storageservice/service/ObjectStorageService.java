package com.job.board.storageservice.service;

import com.job.board.storageservice.model.dto.FileMetadataUploadDto;

import java.io.InputStream;

public interface ObjectStorageService {

    void putObject(FileMetadataUploadDto dto, InputStream inputStream);
}
