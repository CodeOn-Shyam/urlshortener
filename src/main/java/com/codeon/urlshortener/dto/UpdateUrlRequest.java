package com.codeon.urlshortener.dto;

import lombok.Data;

@Data
public class UpdateUrlRequest {
    private Long id;
    private String newOriginalUrl;   
}
