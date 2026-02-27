package com.job.board.storageservice.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

import static com.job.board.storageservice.util.constants.ErrorMessageConstants.UNKNOWN_BUCKET_MESSAGE;

@Getter
@RequiredArgsConstructor
public enum FileBucketType {

    AVATARS("avatars"),
    CVS("cvs"),
    BRANDING("branding"),
    DOCUMENTS("documents");

    private final String name;

    public static FileBucketType fromString(String name) {
        return Stream.of(values())
                .filter(bucketType -> bucketType.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UNKNOWN_BUCKET_MESSAGE.formatted(name)));
    }
}
