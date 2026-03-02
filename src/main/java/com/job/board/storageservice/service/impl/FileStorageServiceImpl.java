package com.job.board.storageservice.service.impl;

import com.job.board.storageservice.mapper.FileMetadataMapper;
import com.job.board.storageservice.model.dto.*;
import com.job.board.storageservice.service.FileMetadataService;
import com.job.board.storageservice.service.FileStorageService;
import com.job.board.storageservice.service.ObjectStorageService;
import com.job.board.storageservice.validator.FileValidator;
import io.minio.GetObjectResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    private final FileMetadataService metadataService;
    private final ObjectStorageService storageService;
    private final FileMetadataMapper metadataMapper;
    private final FileValidator fileValidator;

    @Override
    @SneakyThrows
    public FileSaveResponseDto save(MultipartFile file, String bucket, UUID uploadedBy) {
        FileMetadataUploadDto dto = metadataMapper.toUploadDto(bucket, file.getOriginalFilename(), file.getContentType(), file.getSize(), uploadedBy);
        fileValidator.validate(dto);
        log.debug("Starting file upload: originalName - {}, bucket - {}", dto.originalName(), bucket);
        storageService.save(dto, file.getInputStream());

        return metadataService.save(dto);
    }

    @Override
    public FileMetadataReadDto findById(UUID id) {
        return metadataService.findByIdForRead(id);
    }

    @Override
    public FileResourceDto downloadById(UUID id) {
        FileMetadataDownloadDto dto = metadataService.findByIdForDownload(id);
        InputStream stream = storageService.findByBucketAndStoredName(dto.bucket(), dto.storedName());

        return metadataMapper.toResourceDto(dto, stream);
    }

    @Override
    public void deleteById(UUID id) {
        FileMetadataDeleteDto metadata = metadataService.findByIdForDelete(id);
        storageService.delete(metadata.bucket(), metadata.storedName());
        metadataService.deleteById(id);
    }
}
