package learn.basic.learnbasic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @title: TestProperties
 * @Description TODO
 * @Author ZLT
 * @Date: 2021/11/25 14:36
 * @Version 1.0
 */
@Component
public class TestProperties {

    @Value("${testProperties.name}")
    private String name;

    @Value("${testProperties.age}")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
