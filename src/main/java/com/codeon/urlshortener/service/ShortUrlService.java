package com.codeon.urlshortener.service;

public interface ShortUrlService{
    Long getId(String originalUrl);
    String getOriginalUrl(Long id);
    void deleteUrl(Long id);
    void updateUrl(Long id, String newOriginalUrl);
}