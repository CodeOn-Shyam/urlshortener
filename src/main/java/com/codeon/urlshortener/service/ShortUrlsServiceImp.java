package com.codeon.urlshortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codeon.urlshortener.repository.ShortUrlsRepository;
import com.codeon.urlshortener.entity.Url;

@Service
public class ShortUrlsServiceImp implements ShortUrlService {
    @Autowired
    private ShortUrlsRepository shortUrlsRepository;
    
    @Override
    public Long getId(String originalUrl) {
        Url url = Url.builder().originalUrl(originalUrl).build();
        url = shortUrlsRepository.save(url);
        return url.getId();
    }

    @Override
    public String getOriginalUrl(Long id) {
        return shortUrlsRepository.findById(id).orElseThrow(() -> new RuntimeException("URL not found")).getOriginalUrl();
    }
    @Override
    public void deleteUrl(Long id){
        if(!shortUrlsRepository.existsById(id)){
            throw new RuntimeException("URL not found");
        }
        shortUrlsRepository.deleteById(id);
    }
    @Override
    public void updateUrl(Long id, String newOriginalUrl){
        Url url = shortUrlsRepository.findById(id).orElseThrow(() -> new RuntimeException("URL not found"));
        url.setOriginalUrl(newOriginalUrl);
        shortUrlsRepository.save(url);
    }
}
