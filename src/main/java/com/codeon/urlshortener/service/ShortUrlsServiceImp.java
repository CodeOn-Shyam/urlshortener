package com.codeon.urlshortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codeon.urlshortener.repository.ShortUrlsRepository;
import com.codeon.urlshortener.entity.Url;
import com.codeon.urlshortener.util.Base62Encoder;

@Service
public class ShortUrlsServiceImp implements ShortUrlService {
    @Autowired
    private ShortUrlsRepository shortUrlsRepository;
    
    @Override
    public String getShortUrl(String originalUrl) {
        Url url = Url.builder().originalUrl(originalUrl).build();
        url = shortUrlsRepository.save(url);
        return Base62Encoder.encode(url.getId());
    }

    @Override
    public String getOriginalUrl(String shortUrl) {
        Long id = Base62Encoder.decode(shortUrl);
        return shortUrlsRepository.findById(id).orElseThrow(() -> new RuntimeException("URL not found")).getOriginalUrl();
    }
    @Override
    public void deleteUrl(String shortUrl){
        Long id = Base62Encoder.decode(shortUrl);
        if(!shortUrlsRepository.existsById(id)){
            throw new RuntimeException("URL not found");
        }
        shortUrlsRepository.deleteById(id);
    }
    @Override
    public void updateUrl(String shortUrl, String newOriginalUrl){
        Long id = Base62Encoder.decode(shortUrl);
        Url url = shortUrlsRepository.findById(id).orElseThrow(() -> new RuntimeException("URL not found"));
        url.setOriginalUrl(newOriginalUrl);
        shortUrlsRepository.save(url);
    }
}
