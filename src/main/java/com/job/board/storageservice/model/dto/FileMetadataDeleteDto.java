package com.job.board.storageservice.model.dto;

public record FileMetadataDeleteDto(
        String bucket,
        String storedName
) {
}
