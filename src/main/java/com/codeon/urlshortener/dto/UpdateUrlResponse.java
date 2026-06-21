package com.codeon.urlshortener.dto;
import lombok.Data;
import lombok.Builder;
import java.time.LocalDateTime;
@Data
@Builder
public class UpdateUrlResponse {
  private Long id;
  private String url;
  private String shortCode;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
