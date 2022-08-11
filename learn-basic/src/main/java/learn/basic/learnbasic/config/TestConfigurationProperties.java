package learn.basic.learnbasic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @title: TestConfigurationProperties
 * @Description TODO
 * @Author Ltter
 * @Date: 2021/11/26 11:27
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "cp.tcp")
public class TestConfigurationProperties {

    private String name;

    private Integer age;

    private String ip;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
