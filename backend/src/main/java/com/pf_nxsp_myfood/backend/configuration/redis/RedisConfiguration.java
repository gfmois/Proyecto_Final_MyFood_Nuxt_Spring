package com.pf_nxsp_myfood.backend.configuration.redis;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory rFactory) {
        RedisCacheConfiguration rConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(1));
        
        return RedisCacheManager.builder(rFactory)
            .cacheDefaults(rConfiguration)
            .build();

    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setPassword("eYVX7EwVmmxKPCDmwMtyKVge8oLd2t81"); // TODO: Pass to .ENV
        return new JedisConnectionFactory(configuration);
    }

    @Bean
    RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
