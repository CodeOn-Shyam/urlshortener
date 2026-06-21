package com.codeon.urlshortener.dto;

import lombok.Data;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@Builder
public class AccessCountResponse {
   private Long id;
   private String url;
   private String shortCode;
   private LocalDateTime updatedAt;
   private LocalDateTime createdAt;
   private Long accessCount; 
}
