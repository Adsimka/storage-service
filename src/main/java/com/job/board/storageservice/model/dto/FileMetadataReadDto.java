package com.job.board.storageservice.model.dto;

import java.time.LocalDateTime;

public record FileMetadataReadDto(
        String originalName,
        String storedName,
        String contentType,
        Long sizeBytes,
        LocalDateTime createdAt
) {
}
