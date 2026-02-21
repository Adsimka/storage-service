package com.job.board.storageservice.mapper;

import com.job.board.storageservice.config.MappingConfig;
import com.job.board.storageservice.model.dto.FileMetadataReadDto;
import com.job.board.storageservice.model.entity.FileMetadata;
import org.mapstruct.Mapper;

@Mapper(
        config = MappingConfig.class
)
public interface FileMetadataMapper {

    FileMetadataReadDto toDto(FileMetadata fileMetadata);
}
