package com.codeon.urlshortener.service;
import com.codeon.urlshortener.entity.Url;

public interface ShortUrlService{
    Url getShortCode(String url);
    Url getOriginalUrl(String shortCode);
    void deleteUrl(String shortCode);
    Url updateUrl(String shortCode, String url);
    Url getAccessCount(String shortCode);
}