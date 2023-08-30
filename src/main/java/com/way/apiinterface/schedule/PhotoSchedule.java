package com.way.apiinterface.schedule;

import com.way.apiinterface.utils.CSVUtils;
import com.way.apiinterface.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.FileNotFoundException;

@Component
@Slf4j
public class PhotoSchedule {
    @Resource
    private CommonUtils commonUtils;
    @Scheduled(cron = "0 0 0/1 * * ?")
    @PostConstruct
    public void deleteOutOfDateNonce() {
        log.info("更新图片信息");
        commonUtils.updateGirlAvatars();
        commonUtils.updateBoyAvatars();
        if (CommonUtils.girlAvatars==null ||CommonUtils.boyAvatars==null){
            log.error("图片内容为空..");
        }
    }
}
