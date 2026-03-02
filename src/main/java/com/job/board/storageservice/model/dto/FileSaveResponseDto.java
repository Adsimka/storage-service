package com.job.board.storageservice.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record FileSaveResponseDto(
        UUID id,
        String originalName,
        String contentType,
        Long sizeBytes,
        String bucket,
        LocalDateTime createdAt
) {
}
