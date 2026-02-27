package com.job.board.storageservice.validator;

import com.job.board.storageservice.model.dto.FileMetadataUploadDto;
import com.job.board.storageservice.properties.FileProperties;

public interface FileValidationStrategy {

    void validate(FileMetadataUploadDto dto, FileProperties properties);
}
