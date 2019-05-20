package cn.itcast.consumer.client;

import cn.itcast.consumer.pojo.User;
import org.springframework.stereotype.Component;

/**
 * UserClientImpl
 * hasee
 * 2019/1/29
 * 15:14
 *
 * @Version 1.0
 **/
@Component
public class UserClientFallback implements UserClient {

    @Override
    public User queryById(Long id) {
        User user=new User();
        user.setName("未知用户");
        return user;
    }
}
