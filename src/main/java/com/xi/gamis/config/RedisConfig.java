package com.xi.gamis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class RedisConfig {
    @Autowired
    RedisTemplate redisTemplate;

    public RedisTemplate<String,Object> getTemplate()
    {
        //redisTemplate.opsForSet().
        return null;
    }

}
