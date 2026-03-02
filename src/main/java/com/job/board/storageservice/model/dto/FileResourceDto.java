package com.job.board.storageservice.model.dto;

import lombok.Builder;
import org.springframework.core.io.InputStreamResource;

@Builder
public record FileResourceDto(
        String contentType,
        String originalName,
        InputStreamResource resource
) {
}
