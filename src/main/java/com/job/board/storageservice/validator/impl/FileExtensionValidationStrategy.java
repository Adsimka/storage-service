package com.job.board.storageservice.validator.impl;

import com.job.board.storageservice.exception.FileValidationException;
import com.job.board.storageservice.model.dto.FileMetadataUploadDto;
import com.job.board.storageservice.properties.FileProperties;
import com.job.board.storageservice.validator.FileValidationStrategy;
import org.springframework.stereotype.Component;

import java.util.Locale;

import static com.job.board.storageservice.util.FileNameGenerator.extractExtension;

@Component
public class FileExtensionValidationStrategy implements FileValidationStrategy {

    @Override
    public void validate(FileMetadataUploadDto dto, FileProperties properties) {
        String filename = dto.originalName();
        if (filename == null || filename.isBlank()) {
            throw new FileValidationException("File name is missing");
        }
        String extension = extractExtension(filename).toLowerCase(Locale.ROOT);
        if (extension.isEmpty()) {
            throw new FileValidationException("File must have an extension");
        }
        String extensionWithoutDot = extension.substring(1);
        if (!properties.getAllowedExtensions().contains(extensionWithoutDot)) {
            throw new FileValidationException(
                    "File extension '%s' is not allowed. Allowed extensions: %s"
                            .formatted(extensionWithoutDot, properties.getAllowedExtensions())
            );
        }
    }
}
