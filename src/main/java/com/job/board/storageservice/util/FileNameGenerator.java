package com.job.board.storageservice.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class FileNameGenerator {

    public static String generateStoredName(String originalName) {
        String extension = extractExtension(originalName);
        return UUID.randomUUID() + extension;
    }

    public static String generateStoragePath(String bucketName, String storedName) {
        return bucketName + "/" + storedName;
    }

    public static String extractExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf("."));
    }
}
