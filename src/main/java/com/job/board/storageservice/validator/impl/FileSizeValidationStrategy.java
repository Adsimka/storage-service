package com.job.board.storageservice.validator.impl;

import com.job.board.storageservice.exception.FileValidationException;
import com.job.board.storageservice.model.dto.FileMetadataUploadDto;
import com.job.board.storageservice.properties.FileProperties;
import com.job.board.storageservice.validator.FileValidationStrategy;
import org.springframework.stereotype.Component;

@Component
public class FileSizeValidationStrategy implements FileValidationStrategy {

    @Override
    public void validate(FileMetadataUploadDto dto, FileProperties properties) {
        Long maxSizeBytes = getMaxSizeBytes(properties.getMaxFileSizeMb());
        if (dto.sizeBytes() == 0) {
            throw new FileValidationException("File is empty");
        }
        if (dto.sizeBytes() > maxSizeBytes) {
            throw new FileValidationException(
                    "File size exceeds maximum allowed size of %d MB".formatted(properties.getMaxFileSizeMb())
            );
        }
    }

    private Long getMaxSizeBytes(Integer maxFileSize) {
        return maxFileSize * 1024L * 1024L;
    }
}
