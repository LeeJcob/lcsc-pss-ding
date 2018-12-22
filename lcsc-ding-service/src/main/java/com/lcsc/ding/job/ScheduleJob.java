package com.lcsc.ding.job;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时调度任务
 */
@EnableScheduling
@Component
public class ScheduleJob {

    @Scheduled(cron = "0/5 * *  * * ? ")
    public void test() {

        System.out.println("asdsad");
    }
}
