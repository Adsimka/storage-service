package com.job.board.storageservice.mapper;

import com.job.board.storageservice.config.MappingConfig;
import com.job.board.storageservice.model.dto.*;
import com.job.board.storageservice.model.entity.FileMetadata;
import org.mapstruct.Mapper;
import org.springframework.core.io.InputStreamResource;

import java.io.InputStream;
import java.util.UUID;

import static com.job.board.storageservice.util.FileNameGenerator.generateStoragePath;
import static com.job.board.storageservice.util.FileNameGenerator.generateStoredName;

@Mapper(
        config = MappingConfig.class
)
public interface FileMetadataMapper {

    FileSaveResponseDto toUploadResponseDto(FileMetadata fileMetadata);

    FileMetadataReadDto toReadDto(FileMetadata fileMetadata);

    FileMetadataDeleteDto toDeleteDto(FileMetadata fileMetadata);

    FileMetadata toEntity(FileMetadataUploadDto fileMetadataUploadDto);

    default FileMetadataUploadDto toUploadDto(String bucket, String originalFilename, String contentType, Long sizeBytes, UUID uploadedBy) {
        String storedName = generateStoredName(originalFilename);
        return FileMetadataUploadDto.builder()
                .originalName(originalFilename)
                .storedName(storedName)
                .storagePath(generateStoragePath(bucket, storedName))
                .contentType(contentType)
                .sizeBytes(sizeBytes)
                .uploadedBy(uploadedBy)
                .bucket(bucket)
                .build();
    }

    default FileMetadataDownloadDto toDownloadDto(FileMetadata fileMetadata) {
        return FileMetadataDownloadDto.builder()
                .originalName(fileMetadata.getOriginalName())
                .contentType(fileMetadata.getContentType())
                .bucket(fileMetadata.getBucket())
                .storedName(fileMetadata.getStoredName())
                .build();
    }

    default FileResourceDto toResourceDto(FileMetadataDownloadDto dto, InputStream stream) {
        return FileResourceDto.builder()
                .originalName(dto.originalName())
                .contentType(dto.contentType())
                .resource(new InputStreamResource(stream))
                .build();
    }
}
