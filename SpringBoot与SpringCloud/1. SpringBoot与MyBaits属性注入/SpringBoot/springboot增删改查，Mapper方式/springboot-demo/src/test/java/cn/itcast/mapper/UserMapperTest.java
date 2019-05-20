package cn.itcast.mapper;

import cn.itcast.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;



/**
 * UserMapperTest
 * hasee
 * 2019/1/26
 * 17:46
 *
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    //测试通用Mapper
    @Test
    public void testQuery(){
        User user=userMapper.selectByPrimaryKey(8L);
        System.out.println("user= "+user);
    }
}
