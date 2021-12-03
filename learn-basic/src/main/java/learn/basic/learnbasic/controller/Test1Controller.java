package learn.basic.learnbasic.controller;

import learn.basic.learnbasic.config.TestConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: Test1Controller
 * @Description TODO
 * @Author ZLT
 * @Date: 2021/11/26 11:52
 * @Version 1.0
 */
@RestController
@RequestMapping("/tt")
public class Test1Controller {
    @Autowired
    private TestConfigurationProperties testConfigurationProperties;

    @GetMapping("/showProperties")
    public String showProperties(){
        return testConfigurationProperties.getName()+";"+testConfigurationProperties.getAge();
    }
}
