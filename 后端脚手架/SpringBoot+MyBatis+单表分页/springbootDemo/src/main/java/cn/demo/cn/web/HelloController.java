package cn.demo.cn.web;

import cn.demo.cn.config.JdbcConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @program: HelloController
 * @author: 张磊
 * @create: 2019/5/15-21:46
 **/
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        JdbcConfig jdbcConfig=new JdbcConfig();
        DataSource dataSource=jdbcConfig.dataSource();
        System.out.println(dataSource.toString());
        return "hello,Spring Boot!";
    }
}
