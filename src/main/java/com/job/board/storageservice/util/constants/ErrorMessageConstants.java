package com.job.board.storageservice.util.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessageConstants {

    public static final String FILE_METADATA_NOT_FOUND_MESSAGE = "File metadata with id %s not found";
    public static final String UNKNOWN_BUCKET_MESSAGE = "Unknown bucket: %s. Allowed values: avatars, cvs, branding, documents";

    public static final String FILE_VALIDATION_ERROR_MESSAGE = "File validation error: %s";
    public static final String FILE_UNKNOWN_ERROR_VALIDATION_MESSAGE = "An unknown error occurred while validating the file: %s";

    public static final String ERROR_LOADING_FILE_IN_MINIO_MESSAGE = "Error loading file in MiniO: %s";
}
