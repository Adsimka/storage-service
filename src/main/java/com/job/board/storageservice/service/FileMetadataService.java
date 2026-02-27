package com.job.board.storageservice.service;

import com.job.board.storageservice.model.dto.FileMetadataReadDto;
import com.job.board.storageservice.model.dto.FileMetadataUploadDto;
import com.job.board.storageservice.model.dto.FileUploadResponseDto;

import java.util.UUID;

public interface FileMetadataService {

    FileUploadResponseDto create(FileMetadataUploadDto dto);

    FileMetadataReadDto findById(UUID id);

    void deleteFile(UUID id);
}
