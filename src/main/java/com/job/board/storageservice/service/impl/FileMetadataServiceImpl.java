package com.job.board.storageservice.service.impl;

import com.job.board.storageservice.exception.FileMetadataNotFoundException;
import com.job.board.storageservice.mapper.FileMetadataMapper;
import com.job.board.storageservice.model.dto.FileMetadataReadDto;
import com.job.board.storageservice.model.dto.FileMetadataUploadDto;
import com.job.board.storageservice.model.dto.FileUploadResponseDto;
import com.job.board.storageservice.model.entity.FileMetadata;
import com.job.board.storageservice.repository.FileMetadataRepository;
import com.job.board.storageservice.service.FileMetadataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.job.board.storageservice.util.constants.ErrorMessageConstants.FILE_METADATA_NOT_FOUND_MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileMetadataServiceImpl implements FileMetadataService {

    private final FileMetadataRepository fileMetadataRepository;
    private final FileMetadataMapper metadataMapper;

    @Override
    @Transactional
    public FileUploadResponseDto create(FileMetadataUploadDto dto) {
        FileMetadata fileMetadata = fileMetadataRepository.save(metadataMapper.toEntity(dto));
        return metadataMapper.toUploadResponseDto(fileMetadata);
    }

    @Override
    public FileMetadataReadDto findById(UUID id) {
        log.info("Search FileMetadata for id {}", id);
        return fileMetadataRepository.findById(id)
                .map(metadataMapper::toReadDto)
                .orElseThrow(
                        () -> new FileMetadataNotFoundException(FILE_METADATA_NOT_FOUND_MESSAGE.formatted(id))
                );
    }

    @Override
    public void deleteFile(UUID id) {
        log.info("Delete FileMetadata for id {}", id);
        fileMetadataRepository.deleteById(id);
    }
}
