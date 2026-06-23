package com.codeon.urlshortener.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
@Entity
@Table(name = "urls")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Url {
    @Id
    private Long id;
    @Column(nullable = false)
    private String url;
    @Column(unique = true, nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long accessCount;
}
