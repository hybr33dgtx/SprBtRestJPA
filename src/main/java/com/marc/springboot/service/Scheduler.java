package com.marc.springboot.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Scheduler {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void firstScheduler () {
        logger.info("Current Date : "+new Date());
    }
}
