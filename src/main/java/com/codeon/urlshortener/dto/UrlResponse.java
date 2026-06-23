package com.codeon.urlshortener.dto;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
@Data
@Builder
public class UrlResponse {
    private long id;
    private String url;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
