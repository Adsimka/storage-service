package com.job.board.storageservice.service.impl;

import com.job.board.storageservice.exception.FileMetadataNotFoundException;
import com.job.board.storageservice.mapper.FileMetadataMapper;
import com.job.board.storageservice.model.dto.FileMetadataReadDto;
import com.job.board.storageservice.repository.FileMetadataRepository;
import com.job.board.storageservice.service.FilesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.job.board.storageservice.util.ErrorMessageConstants.FILE_METADATA_NOT_FOUND_MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilesServiceImpl implements FilesService {

    private final FileMetadataMapper filesMapper;

    private final FileMetadataRepository filesRepository;

    @Override
    public FileMetadataReadDto getFileMetadata(UUID id) {
        log.info("Search FileMetadata for id {}", id);
        return filesRepository.findById(id)
                .map(filesMapper::toDto)
                .orElseThrow(
                        () -> new FileMetadataNotFoundException(FILE_METADATA_NOT_FOUND_MESSAGE.formatted(id))
                );
    }

    @Override
    public void deleteFile(UUID id) {
        log.info("Delete FileMetadata for id {}", id);
        filesRepository.deleteById(id);
    }
}
