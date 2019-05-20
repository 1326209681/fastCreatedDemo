package cn.itcast.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloworldController
 * hasee
 * 2018/12/8
 * 16:52
 *
 * @Version 1.0
 **/
@RestController
public class HelloWorldController {

    @Autowired
    private Environment env;
    @RequestMapping("/info")
    public String findString(){
        return "HelloWorld!"+env.getProperty("url");
    }

}
