package com.codeon.urlshortener.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codeon.urlshortener.repository.ShortUrlsRepository;
import com.codeon.urlshortener.entity.Url;
import com.codeon.urlshortener.util.RandomCode;

@Service
public class ShortUrlsServiceImp implements ShortUrlService {
    @Autowired
    private ShortUrlsRepository shortUrlsRepository;
    
    @Override
    public Url getShortCode(String url) {
        String code;
        do{
            code = RandomCode.generateRandomCode(6);
        }while(shortUrlsRepository.existsByShortCode(code));
        Url urls = Url.builder().url(url)
        .shortCode(code)
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .accessCount(0L)
        .build();
        urls = shortUrlsRepository.save(urls);
        return urls;
    }

    @Override
    public Url getOriginalUrl(String shortCode) {
        return shortUrlsRepository.findByShortCode(shortCode)
        .orElseThrow(() -> new RuntimeException("URL not found"));
    }
    @Override
    public void deleteUrl(String shortCode){
        Long id = shortUrlsRepository.findByShortCode(shortCode)
        .orElseThrow(() -> new RuntimeException("URL not found")).getId();
        shortUrlsRepository.deleteById(id);

    }
    @Override
    public Url updateUrl(String shortCode, String newOriginalUrl){
        Url url = shortUrlsRepository.findByShortCode(shortCode).orElseThrow(() -> new RuntimeException("URL not found"));
        url.setUrl(newOriginalUrl);
        url.setUpdatedAt(LocalDateTime.now());
        return shortUrlsRepository.save(url);
    }
    @Override
    public Url getAccessCount(String shortCode){
        return shortUrlsRepository.findByShortCode(shortCode).orElseThrow(()-> new RuntimeException("URL not found"));
    }
}
