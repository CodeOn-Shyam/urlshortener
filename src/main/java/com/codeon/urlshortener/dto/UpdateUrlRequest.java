package com.codeon.urlshortener.dto;

import lombok.Data;

@Data
public class UpdateUrlRequest {
    private String shortUrl;
    private String newOriginalUrl;   
}
