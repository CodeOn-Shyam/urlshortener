package com.codeon.urlshortener.dto;

import lombok.Data;
import lombok.Builder;
import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse{
    private int status;
    private String message;
    private LocalDateTime timestamp;
}