package com.job.board.storageservice.service.impl;

import com.job.board.storageservice.exception.MinioUploadException;
import com.job.board.storageservice.mapper.FileMetadataMapper;
import com.job.board.storageservice.model.dto.FileMetadataReadDto;
import com.job.board.storageservice.model.dto.FileMetadataUploadDto;
import com.job.board.storageservice.model.dto.FileUploadResponseDto;
import com.job.board.storageservice.service.FileMetadataService;
import com.job.board.storageservice.service.FileStorageService;
import com.job.board.storageservice.service.ObjectStorageService;
import com.job.board.storageservice.validator.FileValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static com.job.board.storageservice.util.constants.ErrorMessageConstants.ERROR_LOADING_FILE_IN_MINIO_MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    private final FileMetadataService fileMetadataService;
    private final ObjectStorageService storageService;
    private final FileMetadataMapper metadataMapper;
    private final FileValidator fileValidator;

    @Override
    public FileUploadResponseDto upload(MultipartFile file, String bucket, UUID uploadedBy) {
        var dto = getFileMetadataUploadDto(file, bucket, uploadedBy);
        fileValidator.validate(dto);
        log.debug("Starting file upload: originalName - {}, bucket - {}", dto.originalName(), bucket);
        try {
            storageService.putObject(dto, file.getInputStream());
        } catch (Exception exception) {
            String errorMessage = ERROR_LOADING_FILE_IN_MINIO_MESSAGE.formatted(exception.getMessage());
            log.error(errorMessage, exception);
            throw new MinioUploadException(errorMessage);
        }
        return fileMetadataService.create(dto);
    }

    @Override
    public FileMetadataReadDto getFileMetadata(UUID id) {
        return fileMetadataService.findById(id);
    }

    @Override
    public byte[] downloadFile(UUID id) {
        return new byte[0];
    }

    @Override
    public void deleteFile(UUID id) {
        fileMetadataService.deleteFile(id);
    }

    private FileMetadataUploadDto getFileMetadataUploadDto(MultipartFile file, String bucket, UUID uploadedBy) {
        return metadataMapper.toUploadDto(bucket, file.getOriginalFilename(), file.getContentType(), file.getSize(), uploadedBy);
    }
}
