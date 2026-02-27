package com.job.board.storageservice.controller;

import com.job.board.storageservice.model.dto.FileMetadataReadDto;
import com.job.board.storageservice.model.dto.FileUploadResponseDto;
import com.job.board.storageservice.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileStorageController {

    private final FileStorageService fileStorageService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileUploadResponseDto> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("bucket") String bucket,
            @RequestHeader("X-User-Id") UUID uploadedBy
    ) {
        return ResponseEntity.ok(fileStorageService.upload(file, bucket, uploadedBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileMetadataReadDto> getFileMetadata(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(fileStorageService.getFileMetadata(id));
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(fileStorageService.downloadFile(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable("id") UUID id) {
        fileStorageService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}
