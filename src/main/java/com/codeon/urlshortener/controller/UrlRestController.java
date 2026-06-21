package com.codeon.urlshortener.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.codeon.urlshortener.service.ShortUrlService;

import lombok.experimental.PackagePrivate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.codeon.urlshortener.dto.UpdateUrlRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.codeon.urlshortener.dto.CreateUrlRequest;
import com.codeon.urlshortener.dto.CreateUrlResponse;
import com.codeon.urlshortener.dto.GetUrlResponse;
import com.codeon.urlshortener.dto.UpdateUrlResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.codeon.urlshortener.entity.Url;
@RestController
@RequestMapping("/api")
public class UrlRestController {
    @Autowired
    private ShortUrlService shortUrlService;

    @PostMapping("/shorten")
    public CreateUrlResponse shortenUrl(@RequestBody CreateUrlRequest request){
        Url url = shortUrlService.getShortCode(request.getUrl());
        return CreateUrlResponse.builder()
        .id(url.getId())
        .url(url.getUrl())
        .shortCode(url.getShortCode())
        .createdAt(url.getCreatedAt())
        .updatedAt(url.getUpdatedAt())
        .build();
    }
    
    @GetMapping("/shorten/{shortCode}")
    public GetUrlResponse expandUrl(@PathVariable String shortCode){
        Url url = shortUrlService.getOriginalUrl(shortCode);
        return GetUrlResponse.builder()
        .id(url.getId())
        .url(url.getUrl())
        .shortCode(url.getShortCode())
        .createdAt(url.getCreatedAt())
        .updatedAt(url.getUpdatedAt())
        .build();
    }
    @DeleteMapping("/delete/{shortCode}")
    public void deleteUrl(@PathVariable String shortCode){
        shortUrlService.deleteUrl(shortCode);
    }
    @PutMapping("/shorten/{shortCode}")
    public UpdateUrlResponse updateUrl(@RequestBody UpdateUrlRequest request,@PathVariable String shortCode){
        Url url = shortUrlService.updateUrl(shortCode, request.getUrl());
        return UpdateUrlResponse
        .builder()
        .id(url.getId())
        .url(url.getUrl())
        .shortCode(url.getShortCode())
        .createdAt(url.getCreatedAt())
        .updatedAt(url.getUpdatedAt())
        .build();
    }
}
