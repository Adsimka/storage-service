package com.job.board.storageservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "file_metadata", schema = "storage")
public class FileMetadata {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "original_name", nullable = false, length = 1000)
    private String originalName;

    @Column(name = "stored_name", nullable = false, length = 1000)
    private String storedName;

    @Column(name = "content_type", nullable = false, length = 200)
    private String contentType;

    @Column(name = "size_bytes", nullable = false)
    private Long sizeBytes;

    @Column(name = "bucket", nullable = false, length = 200)
    private String bucket;

    @Column(name = "storage_path", nullable = false, length = 2000)
    private String storagePath;

    @Column(name = "uploaded_by", nullable = false)
    private UUID uploadBy;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
