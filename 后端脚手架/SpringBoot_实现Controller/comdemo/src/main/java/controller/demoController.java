package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demoController
 * @author: 张磊
 * @create: 2019/4/1-23:09
 **/
@RestController
@RequestMapping("/demo")
public class demoController {

    @RequestMapping("/hello")
    public String sayHello(){
        return "hello,world!";
    }
}
