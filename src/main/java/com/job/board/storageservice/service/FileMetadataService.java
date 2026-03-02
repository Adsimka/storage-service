package com.job.board.storageservice.service;

import com.job.board.storageservice.model.dto.*;

import java.util.UUID;

public interface FileMetadataService {

    FileSaveResponseDto save(FileMetadataUploadDto dto);

    FileMetadataReadDto findByIdForRead(UUID id);

    FileMetadataDeleteDto findByIdForDelete(UUID id);

    FileMetadataDownloadDto findByIdForDownload(UUID id);

    void deleteById(UUID id);
}
