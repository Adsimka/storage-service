package com.job.board.storageservice.service;

import com.job.board.storageservice.model.dto.FileMetadataReadDto;
import com.job.board.storageservice.model.dto.FileUploadResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileStorageService {

    FileUploadResponseDto upload(MultipartFile file, String bucket, UUID uploadedBy);

    FileMetadataReadDto getFileMetadata(UUID id);

    byte[] downloadFile(UUID id);

    void deleteFile(UUID id);

}
