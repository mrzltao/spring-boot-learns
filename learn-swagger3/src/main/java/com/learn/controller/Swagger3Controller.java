package com.learn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title Swagger3Controller
 * @Description Swagger3
 * @Author Ltter
 * @Date 2022/8/17 15:14
 * @Version 1.0
 */
@RestController
@RequestMapping("/sw3")
@Api(tags = "Swagger3服务测试")
public class Swagger3Controller {

    @ApiOperation(value = "查询")
    @GetMapping("/find")
    public String find(){
        return "查询-结果-";
    }

    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public String save(){
        return "保存-结果-";
    }

}
