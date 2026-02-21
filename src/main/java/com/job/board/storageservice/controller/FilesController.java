package com.job.board.storageservice.controller;

import com.job.board.storageservice.model.dto.FileMetadataReadDto;
import com.job.board.storageservice.service.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FilesController {

    private final FilesService filesService;

//    @PostMapping("/upload")
//    public ResponseEntity<?> uploadFile() {
//        return ResponseEntity.ok(filesService.upload());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<FileMetadataReadDto> getFileMetadata(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(filesService.getFileMetadata(id));
    }

//    @GetMapping("/{id}/download")
//    public ResponseEntity<?> downloadFile(@PathVariable("id") UUID id) {
//        return ResponseEntity.ok(filesService.downloadFile(id));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable("id") UUID id) {
        filesService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}
