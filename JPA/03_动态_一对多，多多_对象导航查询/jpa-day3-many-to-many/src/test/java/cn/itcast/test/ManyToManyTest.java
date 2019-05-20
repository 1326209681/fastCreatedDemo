package cn.itcast.test;

import cn.itcast.dao.RoleDao;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * ManyToManyTest
 * hasee
 * 2018/12/19
 * 14:55
 *
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    /*
    * 保存一个用户，保存一个角色
    *
    *   多对多放弃维护权： 被动的一方放弃
    * */

    @Test
    @Transactional
    @Rollback(false)
    public void testAdd(){
        User user=new User();
        user.setUsername("小李");

        Role role=new Role();
        role.setRoleName("java程序员");

        /*
        * 因为这两个都可以对外键进行维护，如果两个同时配置了维护，必须其中有一个放弃维护权
        *
        * */
        //配置用户到角色关系，可以对中间表中数据进行维护
        user.getRoles().add(role);
        //配置角色到用户的关系，可以对中间表的数据进行维护
        role.getUsers().add(user);



        userDao.save(user);
        roleDao.save(role);


    }




    /*
    *
    * 级联添加
    *
    * */
    @Test
    @Transactional
    @Rollback(false)
    public void testAdd2(){
        User user=new User();
        user.setUsername("叶修");

        Role role=new Role();
        role.setRoleName("大神");
        //共同操作外键，但role已经放弃了维护权力
        user.getRoles().add(role);

        /*
        * 下面这句是操作外键的，可以不用添加也可以完成保存操作
        *       这个注解是放弃了维护外键，可以使用也可以不使用，当两个维护外键的代码同时存在的时候，就需要这个了；
        *       @ManyToMany(mappedBy = "roles")     //配置多表关系
        *
         *      cascade = CascadeType.ALL
        *       这个注解里面加上了这句话代表了允许级联操作，就表示与他一起的从表将会一起添加数据；
        *
        * */
//        role.getUsers().add(user);

        userDao.save(user);
    }




    /*
    * 级联删除
    *   案例：删除id为1 的用户，同时也删除它的关联对象
    *
    * */
    @Test
    @Transactional
    @Rollback(false)
    public void testCasCadeRemove(){
        //1. 查询1 号用户
        User user=userDao.findOne(1l);
        //2. 删除1号用户
        userDao.delete(user);
    }

}
