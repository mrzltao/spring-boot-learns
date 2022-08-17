package com.learn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Title Knife4jController
 * @Description Knife4j测试接口
 * @Author Ltter
 * @Date 2022/8/17 15:53
 * @Version 1.0
 */
@RestController
@RequestMapping("/knsw")
@Api(tags = "KNIFE4J-SWAGGER服务测试")
public class Knife4jController {

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

    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "编码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "params", value = "参数", dataType = "String", paramType = "query")
    })
    @ApiOperation(value = "查询测试")
    @GetMapping("findALl")
    public String findALl(@RequestParam("code") String code,@RequestParam("params") String params){
        return "返回结果：编码："+ code +" 参数信息："+ params;
    }
}
