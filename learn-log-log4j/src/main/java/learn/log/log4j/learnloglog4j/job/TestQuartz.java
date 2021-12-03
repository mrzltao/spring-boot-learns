package learn.log.log4j.learnloglog4j.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @title: TestQuartz
 * @Description TODO
 * @Author ZLT
 * @Date: 2021/12/2 14:15
 * @Version 1.0
 */
@Component
@EnableScheduling
public class TestQuartz {
    private static Logger logger= LoggerFactory.getLogger(TestQuartz.class);
    @Scheduled(cron = "0/2 * * * * ?")
    public void test(){
        logger.debug("quartz debug");
        logger.info("quartz info");
        logger.warn("quartz warn");
    }
}
