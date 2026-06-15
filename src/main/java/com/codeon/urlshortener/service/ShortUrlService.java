package com.codeon.urlshortener.service;

public interface ShortUrlService{
    String getShortUrl(String originalUrl);
    String getOriginalUrl(String shortUrl);
    void deleteUrl(String shortUrl);
    void updateUrl(String shortUrl, String newOriginalUrl);
}