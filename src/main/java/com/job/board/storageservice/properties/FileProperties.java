package com.job.board.storageservice.properties;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileProperties {

    @Min(1)
    @Max(50)
    private int maxFileSizeMb;

    private int presignedUrlExpiryHours;

    private List<String> allowedExtensions;

    @PostConstruct
    public void init() {
        log.info(toString());
    }
}
