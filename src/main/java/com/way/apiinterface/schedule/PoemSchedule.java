package com.way.apiinterface.schedule;

import com.way.apiinterface.utils.CSVUtils;
import com.way.apiinterface.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.Set;

/**
 * @author Way
 */
@Component
@Slf4j
public class PoemSchedule {
    @Value("${ini.poem-path}")
    private String filePath;

    @Scheduled(cron = "0 0/10 * * * ?")
    @PostConstruct
    public void deleteOutOfDateNonce() {
        log.info("更新古诗");
        try {
            CSVUtils.updatePoems(filePath);
        } catch (FileNotFoundException e) {
            log.error("文件未找到,路径为{}",filePath);
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
