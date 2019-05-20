package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.LinkManDao;
import cn.itcast.domain.Customer;
import cn.itcast.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * ObjectQueryTest
 * hasee
 * 2018/12/19
 * 15:54
 *
 * @Version 1.0
 **/

/*
* 测试对象导航查询
*
*
* */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectQueryTest {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;


    //could no initialize proxy -no Session   没有添加事务--> 添加注解@Transactional
    // 测试对象导航查询
    @Test
    @Transactional
    public void testQuery1(){
        //查询id为1的客户
        Customer customer=customerDao.getOne(3l);
        Set<LinkMan> list=customer.getLinkMans();
        for (LinkMan linkMan : list) {
            System.out.println(linkMan);
        }

    }


    /*
    *  立即加载的查询id值为1的公司的员工表长度
    *  对象导航查询
    *        默认使用的是延迟加载的形式查询的
    *              调用get方法并不会立即发送查询，而是在使用关联对象的时候才会查询
    *
    *        延迟加载！
    *   修改配置，将延迟加载改为立即加载
    *           fetch: 需要配置到多表映射关系的注解上；
    * */
    @Test
    @Transactional
    public void testQuery2(){
        Customer customer=customerDao.findOne(3l);
        Set<LinkMan> linkMans=customer.getLinkMans();
        System.out.println(linkMans.size());
    }




    /*
    * 从联系人对象导航查询他的所属客户
    *
    *   注意：如下方所示案例一样
    *           1. 在查询所有多对一数据的时候，默认：立即加载
    *           2. 在查询所有一对多数据的时候，默认： 延迟加载
    *           3. 在查询的时候如果需要修改默认机制：在所在映射类上的相关地方添加注解fetch...设置加载机制
    *           4. 一般情况下，一对多适合延迟加载，多对一适合立即加载！
    *
    *
    *
    * */
    @Test
    @Transactional
    public void testQuery3(){
        LinkMan linkMan=linkManDao.findOne(3l);
        //对象导航查询所属的客户
        Customer customer=linkMan.getCustomer();
        System.out.println(customer);
    }



}
