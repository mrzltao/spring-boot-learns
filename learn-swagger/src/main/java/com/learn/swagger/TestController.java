package com.learn.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Title TestController
 * @Description 测试
 * @Author Ltter
 * @Date 2022/8/17 9:29
 * @Version 1.0
 */
@RestController
@RequestMapping("/sw")
@Api(tags = "Swagger服务-Test")
public class TestController {

    @ApiOperation(value = "查询")
    @GetMapping("/find")
    public String find(){
        return "查询-结果";
    }

    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public String save(){
        return "保存-结果";
    }

}
