package com.job.board.storageservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResponseDto {

    private UUID id;

    private String originalName;

    private String contentType;

    private Long sizeBytes;

    private String bucket;

    private LocalDateTime createdAt;
}
