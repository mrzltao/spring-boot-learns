package learn.log.log4j2.learnloglog4j2.job;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @title: TestJob
 * @Description TODO
 * @Author ZLT
 * @Date: 2021/12/2 14:09
 * @Version 1.0
 */
@Component
@EnableScheduling
public class TestJob {
    @Scheduled(cron = "0/2 * * * * ?")
    public void test(){
        String s="";
        Integer i=0;
    }
}
