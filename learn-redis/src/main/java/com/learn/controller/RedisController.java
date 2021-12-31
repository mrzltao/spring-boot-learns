package com.learn.controller;

import com.learn.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @title: RedisController
 * @Description TODO
 * @Author ZLT
 * @Date: 2021/12/31 9:46
 * @Version 1.0
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/test")
    public void test(){
        redisUtils.set("name","zhangsan");
        String name = redisUtils.get("name");
        System.out.println(name);
    }
}
