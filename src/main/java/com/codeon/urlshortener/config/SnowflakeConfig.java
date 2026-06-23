package com.codeon.urlshortener.config;

import com.codeon.urlshortener.util.SnowflakeGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnowflakeConfig {

    @Value("${snowflake.machine-id}")
    private long machineId;

    @Bean
    public SnowflakeGenerator snowflakeGenerator() {
        return new SnowflakeGenerator(machineId);
    }
}