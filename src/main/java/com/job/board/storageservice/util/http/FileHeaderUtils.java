package com.job.board.storageservice.util.http;

import lombok.experimental.UtilityClass;
import org.springframework.http.ContentDisposition;

import java.nio.charset.StandardCharsets;

@UtilityClass
public class FileHeaderUtils {

    public static String headers(String fileName) {
        return ContentDisposition.attachment()
                .filename(fileName, StandardCharsets.UTF_8)
                .build()
                .toString();
    }
}
