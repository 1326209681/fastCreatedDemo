package cn.itcast.demo;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 * hasee
 * 2018/12/10
 * 21:32
 *
 * @Version 1.0
 **/
@RestController
public class UserController {

    @RequestMapping("/findLoginUser")
    public void findLoginUser() {
        //当前登录名
        String name=SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("当前登录名：" + name);

    }


}
