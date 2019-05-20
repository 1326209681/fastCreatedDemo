package com.itheima;

import com.itheima.domain.User;
import com.itheima.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * UserRepositoryTest
 * hasee
 * 2018/12/20
 * 18:33
 *
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootJpaApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test1(){
        List<User> list=userRepository.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }
}
