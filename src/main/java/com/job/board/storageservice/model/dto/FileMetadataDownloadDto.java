package com.job.board.storageservice.model.dto;

import lombok.Builder;

@Builder
public record FileMetadataDownloadDto(
        String contentType,
        String originalName,
        String bucket,
        String storedName
) {
}
