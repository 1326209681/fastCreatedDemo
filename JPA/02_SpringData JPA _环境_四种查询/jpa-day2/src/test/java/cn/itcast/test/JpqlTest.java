package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * JpqlTest
 * hasee
 * 2018/12/17
 * 14:41
 *
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)         //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")           //指定spring容器的配置信息
public class JpqlTest {
    @Autowired
    private CustomerDao customerDao;



    /*
    *
    * 根据名称查询
    * */
    @Test
    public  void testFindJPQL(){
        Customer customer=customerDao.findjpql("暗余大神");
        System.out.println(customer);
    }



    /*
    *
    * 根据名称和id值进行查询
    *
    *   1. 对于多个占位符参数
    *       * 赋值的时候，默认的情况下，占位符的位置需要和方法参数中的位置保持一致
    *       * 举例：
    *                1. test：
    *                 Customer customer=customerDao.findCustNameAndId("暗余大神",2l);
                      System.out.println(customer);
                     2. interface：
                         @Query(value = "from Customer where custName = ? and custId = ? ")
                         public  Customer findCustNameAndId(String name,Long id);



    *   2. 可以指定占位符参数的位置
    *       * ？索引的方式，指定此占位的取值来源
    *       * 举例：
    *                1. test：
    *                 Customer customer=customerDao.findCustNameAndId(2l,"暗余大神");
                      System.out.println(customer);
    *               2. interface：
                         @Query(value = "from Customer where custName = ?2 and custId = ?1 ")
                         public  Customer findCustNameAndId(Long id,String name);
    *
    * */
    @Test
    public  void testFindCustNameAndId(){
        Customer customer=customerDao.findCustNameAndId("暗余大神",2l);
        System.out.println(customer);

    }


    /*
    * 更新
    *       springDataJpa中使用jpql完成  更新/删除操作
    *           * 需要手动添加事务的支持
    *           * 默认会执行结束后，回滚事务
    *           * 这里的更新操作不会先清除原有的内容，而是覆盖指定的内容，之前存在的没被修改的依然存在；
    *       @Rollback:  设置是否自动回滚
    *           false/ true
    * */
    @Test
    @Transactional          //添加事务的支持
    @Rollback(false)
    public void testUpdateCustomer(){
      customerDao.updateCustomer(4l,"程序员");
    }





}
