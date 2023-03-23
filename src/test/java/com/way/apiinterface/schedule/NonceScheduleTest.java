package com.way.apiinterface.schedule;

import com.way.apiinterface.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ZSetOperations;

import javax.annotation.Resource;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class NonceScheduleTest {
    @Resource
    private RedisUtils redisUtils;

    @Test
    public void checkTimeStampTest(){
        ZSetOperations<String, String> ops = redisUtils.getStringRedisTemplate().opsForZSet();
        long max = System.currentTimeMillis() - 5*1000;
        Set<String> nonceSet = ops.rangeByScore("name_nonce", 1, max);
        System.out.println(nonceSet.toString());
        log.info("{}",nonceSet.toString());
//        ops.removeRangeByScore("name_nonce", 1, max);
    }

}