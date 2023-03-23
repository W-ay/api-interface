package com.way.apiinterface.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import javax.annotation.Resource;

@SpringBootTest
class NameControllerTest {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void redisTest() {
        ZSetOperations ops = stringRedisTemplate.opsForZSet();
        String key = "zset_test";
        ops.add(key,"saf11",1);
        Double score = ops.score(key, "saf11");
        Assertions.assertEquals(score,1);
    }

}