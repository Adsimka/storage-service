package com.job.board.storageservice.service;

import com.job.board.storageservice.model.dto.FileMetadataReadDto;
import com.job.board.storageservice.model.dto.FileResourceDto;
import com.job.board.storageservice.model.dto.FileSaveResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface FileStorageService {

    FileSaveResponseDto save(MultipartFile file, String bucket, UUID uploadedBy) throws IOException;

    FileMetadataReadDto findById(UUID id);

    FileResourceDto downloadById(UUID id);

    void deleteById(UUID id);

}
