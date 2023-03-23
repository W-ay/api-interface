package com.way.apiinterface.schedule;

import com.way.apiinterface.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author Way
 */
@Component
@Slf4j
public class NonceSchedule {
    @Resource
    private RedisUtils redisUtils;

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void deleteOutOfDateNonce() {
        log.info("定时任务开始执行,检查是否有过期的nonce");
        ZSetOperations<String, String> ops = redisUtils.getStringRedisTemplate().opsForZSet();
        long max = System.currentTimeMillis() - 5*1000;
        Set<String> nonceSet = ops.rangeByScore("name_nonce", 1, max);
        log.info("当前过期nonce : {}", nonceSet.toString());
        ops.removeRangeByScore("name_nonce", 1, max);

    }
}
