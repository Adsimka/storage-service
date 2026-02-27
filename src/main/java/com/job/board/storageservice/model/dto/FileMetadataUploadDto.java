package com.job.board.storageservice.model.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record FileMetadataUploadDto(
        String originalName,
        String contentType,
        String storagePath,
        String storedName,
        String bucket,
        Long sizeBytes,
        UUID uploadedBy
) {
}
