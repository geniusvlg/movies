package com.fact.nash.extension.caching.redis_cache;

import jdk.jfr.Timespan;

import java.util.List;

public interface RedisCache<T> {

    void set(String key, T object, Timespan expiry);

    T get(String key);

    List<String> getAllKeys();

    List<T> get(String[] key);
}
