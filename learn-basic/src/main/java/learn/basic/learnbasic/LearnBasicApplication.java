package learn.basic.learnbasic;

import learn.basic.learnbasic.config.TestConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TestConfigurationProperties.class)
public class LearnBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnBasicApplication.class, args);
    }

}
