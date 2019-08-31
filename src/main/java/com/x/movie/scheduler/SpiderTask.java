package com.x.movie.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author caisd1
 * @Date 2019-8-29
 * @Version V1.0
 **/
@Slf4j
@Component
public class SpiderTask {
    @Scheduled(cron = "0/5 * * * * *")
    public void task(){

    }
}
