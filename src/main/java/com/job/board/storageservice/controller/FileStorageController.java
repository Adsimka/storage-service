package com.job.board.storageservice.controller;

import com.job.board.storageservice.model.dto.FileMetadataReadDto;
import com.job.board.storageservice.model.dto.FileResourceDto;
import com.job.board.storageservice.model.dto.FileSaveResponseDto;
import com.job.board.storageservice.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

import static com.job.board.storageservice.util.http.FileHeaderUtils.headers;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileStorageController {

    private final FileStorageService fileStorageService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileSaveResponseDto> save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("bucket") String bucket,
            @RequestHeader("X-User-Id") UUID uploadedBy
    ) throws IOException {
        return ResponseEntity.ok(fileStorageService.save(file, bucket, uploadedBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileMetadataReadDto> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(fileStorageService.findById(id));
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<InputStreamResource> downloadById(@PathVariable("id") UUID id) {
        FileResourceDto dto = fileStorageService.downloadById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dto.contentType()))
                .header(CONTENT_DISPOSITION, headers(dto.originalName()))
                .body(dto.resource());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") UUID id) {
        fileStorageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
