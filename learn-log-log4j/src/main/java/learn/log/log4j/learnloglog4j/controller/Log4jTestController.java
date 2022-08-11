package learn.log.log4j.learnloglog4j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: Log4jTestController
 * @Description TODO
 * @Author Ltter
 * @Date: 2021/12/2 9:37
 * @Version 1.0
 */
@RestController
@RequestMapping("log")
public class Log4jTestController {
    private static Logger logger = LoggerFactory.getLogger(Log4jTestController.class);

    @PostMapping("test")
    public String test(){
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        return "success";
    }
}
