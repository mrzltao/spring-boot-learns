package learn.statics.learnstatics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @title: StaticController
 * @Description Spring Boot访问静态资源
 * @Author ZLT
 * @Date: 2021/12/14 10:23
 * @Version 1.0
 */
//@RestController：返回页面不能用RestController,@RestController返回的是JSON格式数据。
@Controller
@RequestMapping("/index")
public class StaticController {

    @GetMapping("/hi")
    public String hi(){
        return "index";
    }
}
