package learn.basic.learnbasic.controller;

import learn.basic.learnbasic.config.TestConfigurationProperties;
import learn.basic.learnbasic.config.TestProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: TestController
 * @Description TODO
 * @Author Ltter
 * @Date: 2021/11/25 14:38
 * @Version 1.0
 */
@RestController
@RequestMapping("/test")
@EnableConfigurationProperties(TestConfigurationProperties.class)
public class TestController {

    @Value("${testProperties.name}")
    private String name;

    @Value("${testProperties.age}")
    private Integer age;

    @Value("${server.address}")
    private String ip;

    @Autowired
    private TestConfigurationProperties testConfigurationProperties;


    @GetMapping("/show")
    public String showTest(){
        TestProperties testProperties=new TestProperties();
        return "值: "+name+";"+age+";"+ip;
    }

    @GetMapping("/showProperties")
    public String showProperties(){
        return testConfigurationProperties.getName()+";"+testConfigurationProperties.getAge();
    }
}
