package com.learn.controller;

import com.alibaba.fastjson.JSONObject;
import com.learn.entity.MlTest;
import com.learn.service.MlTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title MlTestController
 * @Description MlTestController
 * @Author Ltter
 * @Date 2022/8/18 14:59
 * @Version 1.0
 */
@RestController
@RequestMapping("/ml")
public class MlTestController {

    @Autowired
    private MlTestService mlTestService;

    @GetMapping("/find")
    public Map<String, Object> find(){
        List<MlTest> list = mlTestService.find();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code", 0);
        resultMap.put("message", "success");
        resultMap.put("data", JSONObject.toJSONString(list));
        return resultMap;
    }

    @PostMapping("/save")
    public Map<String, Object> save(@RequestBody MlTest mlTest){
        Boolean result = mlTestService.save(mlTest);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code", 0);
        resultMap.put("message", "success");
        resultMap.put("data", result);
        return resultMap;
    }
}
