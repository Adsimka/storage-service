package com.job.board.storageservice.config;

import com.job.board.storageservice.exception.InitializeBucketsException;
import com.job.board.storageservice.model.type.FileBucketType;
import com.job.board.storageservice.properties.MinioProperties;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import static com.job.board.storageservice.util.constants.MinioErrorCodes.BUCKET_ALREADY_EXISTS;
import static com.job.board.storageservice.util.constants.MinioErrorCodes.BUCKET_ALREADY_OWNED_BY_YOU;

@Slf4j
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfig {

    @Bean
    public MinioClient minioClient(MinioProperties minioProperties) {
        MinioClient client = MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
        initializeBuckets(client);

        return client;
    }

    private void initializeBuckets(MinioClient client) {
        Arrays.stream(FileBucketType.values()).forEach(bucketType -> {
            String bucketName = bucketType.getName();
            try {
                client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            } catch (ErrorResponseException exception) {
                String errorCode = exception.errorResponse().code();
                if (BUCKET_ALREADY_OWNED_BY_YOU.equals(errorCode) || BUCKET_ALREADY_EXISTS.equals(errorCode)) {
                    log.debug("Bucket '{}' already exists", bucketName);
                } else {
                    throw new InitializeBucketsException("Failed to create bucket %s".formatted(bucketName), exception);
                }
            } catch (Exception exception) {
                String errorMessage = "Error in initializing bucket - %s".formatted(bucketName);
                log.error(errorMessage, exception);
                throw new InitializeBucketsException(errorMessage, exception);
            }
        });
    }
}
