package com.fact.nash.extension.caching.redis_cache;

import jdk.jfr.Timespan;

import java.util.List;

public class RedisCacheImpl<T> implements RedisCache<T>{
    @Override
    public void set(String key, T object, Timespan expiry) {

    }

    @Override
    public T get(String key) {
        return null;
    }

    @Override
    public List<String> getAllKeys() {
        return null;
    }

    @Override
    public List<T> get(String[] key) {
        return null;
    }
}
