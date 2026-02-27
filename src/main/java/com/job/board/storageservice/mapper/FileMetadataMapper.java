package com.job.board.storageservice.mapper;

import com.job.board.storageservice.config.MappingConfig;
import com.job.board.storageservice.model.dto.FileMetadataReadDto;
import com.job.board.storageservice.model.dto.FileMetadataUploadDto;
import com.job.board.storageservice.model.dto.FileUploadResponseDto;
import com.job.board.storageservice.model.entity.FileMetadata;
import org.mapstruct.Mapper;

import java.util.UUID;

import static com.job.board.storageservice.util.FileNameGenerator.generateStoragePath;
import static com.job.board.storageservice.util.FileNameGenerator.generateStoredName;

@Mapper(
        config = MappingConfig.class
)
public interface FileMetadataMapper {

    FileUploadResponseDto toUploadResponseDto(FileMetadata fileMetadata);

    FileMetadataReadDto toReadDto(FileMetadata fileMetadata);

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

}
