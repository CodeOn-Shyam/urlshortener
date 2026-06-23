package com.codeon.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codeon.urlshortener.entity.Url;

public interface ShortUrlsRepository extends JpaRepository<Url, Long> {
}
