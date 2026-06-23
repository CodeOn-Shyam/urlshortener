package com.codeon.urlshortener.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import com.codeon.urlshortener.repository.ShortUrlsRepository;
import com.codeon.urlshortener.entity.Url;
import com.codeon.urlshortener.util.SnowflakeGenerator;
import com.codeon.urlshortener.util.Base62Encoder;

@Service
public class ShortUrlsServiceImp implements ShortUrlService {
    private final ShortUrlsRepository shortUrlsRepository;
    private final SnowflakeGenerator snowflakeGenerator;

    ShortUrlsServiceImp(ShortUrlsRepository shortUrlsRepository, SnowflakeGenerator snowflakeGenerator) {
        this.shortUrlsRepository = shortUrlsRepository;
        this.snowflakeGenerator = snowflakeGenerator;
    }
    @Override
    public Url getShortCode(String url) {

        long id = snowflakeGenerator.nextId();
        String code = Base62Encoder.encode(id);
        Url urls = Url.builder().url(url)
        .id(id)
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
        long id = Base62Encoder.decode(shortCode);
        Url url = shortUrlsRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("URL not found"));
        url.setAccessCount(url.getAccessCount()+1);
        return shortUrlsRepository.save(url);
    }
    @Override
    public void deleteUrl(String shortCode){
        Long id = Base62Encoder.decode(shortCode);
        shortUrlsRepository.deleteById(id);
    }
    @Override
    public Url updateUrl(String shortCode, String newOriginalUrl){
        long id = Base62Encoder.decode(shortCode);
        Url url = shortUrlsRepository.findById(id).orElseThrow(() -> new RuntimeException("URL not found"));
        url.setUrl(newOriginalUrl);
        url.setUpdatedAt(LocalDateTime.now());
        return shortUrlsRepository.save(url);
    }
    @Override
    public Url getAccessCount(String shortCode){
        long id = Base62Encoder.decode(shortCode);
        return shortUrlsRepository.findById(id).orElseThrow(()-> new RuntimeException("URL not found"));
    }
}
