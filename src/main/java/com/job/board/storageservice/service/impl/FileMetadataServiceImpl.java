package com.job.board.storageservice.service.impl;

import com.job.board.storageservice.exception.FileMetadataNotFoundException;
import com.job.board.storageservice.mapper.FileMetadataMapper;
import com.job.board.storageservice.model.dto.*;
import com.job.board.storageservice.model.entity.FileMetadata;
import com.job.board.storageservice.repository.FileMetadataRepository;
import com.job.board.storageservice.service.FileMetadataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.function.Function;

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
    public FileSaveResponseDto save(FileMetadataUploadDto dto) {
        FileMetadata fileMetadata = fileMetadataRepository.save(metadataMapper.toEntity(dto));
        return metadataMapper.toUploadResponseDto(fileMetadata);
    }

    @Override
    public FileMetadataReadDto findByIdForRead(UUID id) {
        return findById(id,metadataMapper::toReadDto);
    }

    @Override
    public FileMetadataDeleteDto findByIdForDelete(UUID id) {
        return findById(id, metadataMapper::toDeleteDto);
    }

    @Override
    public FileMetadataDownloadDto findByIdForDownload(UUID id) {
        return findById(id, metadataMapper::toDownloadDto);
    }


    @Override
    public void deleteById(UUID id) {
        log.info("Delete FileMetadata for id {}", id);
        fileMetadataRepository.deleteById(id);
    }

    private <D> D findById(UUID id, Function<FileMetadata, D> mapper) {
        log.info("Search FileMetadata for id {}", id);
        return fileMetadataRepository.findById(id)
                .map(mapper)
                .orElseThrow(
                        () -> new FileMetadataNotFoundException(FILE_METADATA_NOT_FOUND_MESSAGE.formatted(id))
                );
    }
}
