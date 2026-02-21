package com.job.board.storageservice.service;

import com.job.board.storageservice.model.dto.FileMetadataReadDto;

import java.util.UUID;

public interface FilesService {

    FileMetadataReadDto getFileMetadata(UUID id);

    void deleteFile(UUID id);
}
