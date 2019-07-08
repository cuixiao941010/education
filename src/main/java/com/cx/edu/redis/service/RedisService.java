package com.cx.edu.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component("redisService")
@SuppressWarnings("unchecked")
public class RedisService<K, V> {

    @Autowired
    private RedisTemplate redisTemplate;

    public void setValue(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getValue(K key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteValue(K key) {
        redisTemplate.delete(key);
    }

}
