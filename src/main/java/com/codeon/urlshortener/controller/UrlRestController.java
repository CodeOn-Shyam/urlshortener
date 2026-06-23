package com.codeon.urlshortener.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;

import com.codeon.urlshortener.service.ShortUrlService;
import com.codeon.urlshortener.dto.AccessCountResponse;
import com.codeon.urlshortener.dto.UrlRequest;
import com.codeon.urlshortener.dto.UrlResponse;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codeon.urlshortener.entity.Url;
@RestController
@RequestMapping("/api")
public class UrlRestController {
    private final ShortUrlService shortUrlService;

    UrlRestController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody UrlRequest request){
        Url url = shortUrlService.getShortCode(request.getUrl());
        return ResponseEntity.status(HttpStatus.CREATED).body(UrlResponse.builder()
        .id(url.getId())
        .url(url.getUrl())
        .shortCode(url.getShortCode())
        .createdAt(url.getCreatedAt())
        .updatedAt(url.getUpdatedAt())
        .build());
    }
    
    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlResponse> expandUrl(@PathVariable String shortCode){
        Url url = shortUrlService.getOriginalUrl(shortCode);
        return ResponseEntity.status(HttpStatus.FOUND).body(UrlResponse.builder()
        .id(url.getId())
        .url(url.getUrl())
        .shortCode(url.getShortCode())
        .createdAt(url.getCreatedAt())
        .updatedAt(url.getUpdatedAt())
        .build());
    }
    @DeleteMapping("/delete/{shortCode}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String shortCode){
        shortUrlService.deleteUrl(shortCode);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/update/{shortCode}")
    public ResponseEntity<UrlResponse> updateUrl(@RequestBody UrlRequest request,@PathVariable String shortCode){
        Url url = shortUrlService.updateUrl(shortCode, request.getUrl());
        return ResponseEntity.ok().body(UrlResponse
        .builder()
        .id(url.getId())
        .url(url.getUrl())
        .shortCode(url.getShortCode())
        .createdAt(url.getCreatedAt())
        .updatedAt(url.getUpdatedAt())
        .build());
    }
    @GetMapping("/stats/{shortCode}")
    public ResponseEntity<AccessCountResponse> getAccessCount(@PathVariable String shortCode){
        Url url = shortUrlService.getAccessCount(shortCode);
        return 
        ResponseEntity.ok().body(AccessCountResponse.builder()
        .id(url.getId())
        .url(url.getUrl())
        .shortCode(url.getShortCode())
        .updatedAt(url.getUpdatedAt())
        .createdAt(url.getCreatedAt())
        .accessCount(url.getAccessCount())
        .build());
    }
}
