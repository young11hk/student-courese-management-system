package com.shanzhu.sc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class CacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 缓存前缀
    private static final String COURSE_CACHE_PREFIX = "sms:course:";
    private static final String PROFESSION_CACHE_PREFIX = "sms:profession:";

    /**
     * 缓存课程列表
     */
    public void cacheCourseList(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(COURSE_CACHE_PREFIX + key, value, timeout, unit);
    }

    /**
     * 获取缓存的课程列表
     */
    public Object getCachedCourseList(String key) {
        return redisTemplate.opsForValue().get(COURSE_CACHE_PREFIX + key);
    }

    /**
     * 缓存专业列表
     */
    public void cacheProfessionList(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(PROFESSION_CACHE_PREFIX + key, value, timeout, unit);
    }

    /**
     * 获取缓存的专业列表
     */
    public Object getCachedProfessionList(String key) {
        return redisTemplate.opsForValue().get(PROFESSION_CACHE_PREFIX + key);
    }

    /**
     * 清除课程缓存
     */
    public void clearCourseCache() {
        Set<String> keys = redisTemplate.keys(COURSE_CACHE_PREFIX + "*");
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 清除专业缓存
     */
    public void clearProfessionCache() {
        Set<String> keys = redisTemplate.keys(PROFESSION_CACHE_PREFIX + "*");
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 清除所有缓存
     */
    public void clearAllCache() {
        clearCourseCache();
        clearProfessionCache();
    }
}