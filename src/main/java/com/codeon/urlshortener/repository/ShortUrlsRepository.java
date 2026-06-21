package com.codeon.urlshortener.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codeon.urlshortener.entity.Url;

public interface ShortUrlsRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortCode(String shortCode);
    boolean existsByShortCode(String shortCode);
}
