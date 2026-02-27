package com.job.board.storageservice.validator;

import com.job.board.storageservice.exception.FileValidationException;
import com.job.board.storageservice.model.dto.FileMetadataUploadDto;
import com.job.board.storageservice.properties.FileProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.job.board.storageservice.util.constants.ErrorMessageConstants.FILE_UNKNOWN_ERROR_VALIDATION_MESSAGE;
import static com.job.board.storageservice.util.constants.ErrorMessageConstants.FILE_VALIDATION_ERROR_MESSAGE;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileValidator {

    private final FileProperties properties;
    private final List<FileValidationStrategy> validationStrategies;

    public void validate(FileMetadataUploadDto dto) {
        validationStrategies.forEach(strategy -> {
            try {
                strategy.validate(dto, properties);
            } catch (FileValidationException exception) {
                log.error(FILE_VALIDATION_ERROR_MESSAGE.formatted(exception.getMessage()), exception);
                throw exception;
            } catch (Exception exception) {
                String errorMessage = FILE_UNKNOWN_ERROR_VALIDATION_MESSAGE.formatted(exception.getMessage());
                log.error(errorMessage, exception);
                throw new FileValidationException(errorMessage);
            }
        });
        log.debug("Validating file {}", dto.originalName());
    }
}
