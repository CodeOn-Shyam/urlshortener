package com.codeon.urlshortener.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.codeon.urlshortener.service.ShortUrlService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.codeon.urlshortener.dto.UpdateUrlRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.codeon.urlshortener.dto.CreateUrlRequest;
import com.codeon.urlshortener.dto.GetUrlRequest;
import com.codeon.urlshortener.dto.DeleteUrlRequest;
@RestController
public class UrlController {
    @Autowired
    private ShortUrlService shortUrlService;

    @PostMapping("/shorten")
    public Long shortenUrl(@RequestBody CreateUrlRequest request){
        return shortUrlService.getId(request.getOriginalUrl());
    }
    
    @GetMapping("/expand")
    public String expandUrl(@RequestBody GetUrlRequest request){
        return shortUrlService.getOriginalUrl(request.getId());
    }
    @DeleteMapping("/delete")
    public void deleteUrl(@RequestBody DeleteUrlRequest request){
        shortUrlService.deleteUrl(request.getId());
    }
    @PutMapping("/update")
    public void updateUrl(@RequestBody UpdateUrlRequest request){
        shortUrlService.updateUrl(request.getId(), request.getNewOriginalUrl());
    }
}
