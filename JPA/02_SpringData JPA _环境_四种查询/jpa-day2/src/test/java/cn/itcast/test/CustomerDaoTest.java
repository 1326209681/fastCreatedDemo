package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CustomerDaoTest
 * hasee
 * 2018/12/17
 * 10:41
 *
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)         //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")       //指定spring容器的配置信息
public class CustomerDaoTest {


    //从容器中获取一个dao对象操作
    @Autowired
    private CustomerDao customerDao;


    /*
    *
    * 根据id查询
    * */
    @Test
    public void TestFindOne(){
        Customer one=customerDao.findOne((long)2);
        System.out.println(one);

    }

    /*
    *
    * save：保存或者更新
    *   1. 根据传递的对象是否存在主键id，
    *       * 如果没有id主键属性： 保存
    *       * 存在id主键属性，根据id查询数据，更新数据
    *   2. 传入的id值为long类型，所以有两种给值方法：
    *       * (long)值
    *       * 值l
    *   3. 它的原理是先对数据库该条数据的主键id值进行判断，如果已有id值则为update，删除之前的所有内容，如果没有id则为save；
    * */

    /*
    * 保存
    * */
    @Test
    public  void TestSave(){
        //创建一个实体类
        Customer customer=new Customer();
        //为实体类赋值
        customer.setCustName("张碧晨");
        customer.setCustId((long)5);
        //调用customerDao进行保存操作
        customerDao.save(customer);
        //调用customerDao查询是否已经保存成功
        Customer one=customerDao.findOne(5l);
        System.out.println(one);
    }

/*
* 更新
* */
    @Test
    public void testUpdate(){
        //创建实体类并给值
        Customer customer=new Customer();
        customer.setCustId(5l);
        customer.setCustLevel("最高等级");
        //调用save操作，判断数据库该id是否存在，存在则进行修改，删除原该id值行的所有内容
        customerDao.save(customer);
        //调用查询方法检查数据是否已修改
        Customer one=customerDao.findOne(5l);
        System.out.println(one);
    }



    /*
    *
    * 删除
    *
    *   注意：
    *       1. 删除的原理，它先进行查询该id所对应的是否有数据
    *           * 如果有则进行删除操作
    *           * 如果没有所对应的id值则抛出异常，删除失败；
    *       2. 删除成功后再查询会返回null，则可以进行判断；
    * */
    @Test
    public void testDelete(){
        //执行删除操作
        customerDao.delete(4l);
        //执行查询操作
        Customer one=customerDao.findOne(5l);
        if (one==null){
            System.out.println("您已成功删除");
        }
    }

    /*
    *
    * 查询所有
    * */
    @Test
    public void testFindAll(){
        List<Customer> list=customerDao.findAll();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }



    /*
    *
    * count： 统计
    * */
    @Test
    public void testCount(){
        long count=customerDao.count();
        System.out.println(count);
    }

    /*
    * 测试：判断id为4的客户是否存在
    *   1. 可以查询以下id为4的客户
    *       * 如果值为空，代表不存在
    *       * 如果不为空，代表存在
    *
    *   2. 判断数据库中id为4的客户的数量
    *       * 如果数量为0，代表不存在
    *       * 如果数量大于0，代表存在
    *
    *   3. 我们SpringData JPA使用的是判断数量；
    * */

    @Test
    public  void testExists(){
        boolean exists=customerDao.exists(10l);
        if ((exists+"").equals("true")){
            System.out.println("存在");
        }else {
            System.out.println("不存在");
        }
    }


    /*
    * 根据id从数据库中查询      :需要添加注解@Transactional(保证程序正常运行)，否则会报错；
    *   1. findOne：
    *           em.find();          立即加载
    *   2. getOne：
    *           em.getReference     延迟加载
    *           * 返回的是一个客户的动态代理对象
    *           * 什么时候用，什么时候查询
    *
    *
    * */
    @Test
    @Transactional
    public  void  testGetOne(){
        Customer one=customerDao.getOne(2l);
        System.out.println(one);

    }


}

