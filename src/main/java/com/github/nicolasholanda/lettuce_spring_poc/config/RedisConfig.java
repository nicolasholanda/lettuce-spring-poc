package com.github.nicolasholanda.lettuce_spring_poc.config;

import io.lettuce.core.RedisClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Value("${redis.url}")
    private String redisUrl;

    @Bean
    public RedisClient redisClient() {
        return RedisClient.create(redisUrl);
    }
}
