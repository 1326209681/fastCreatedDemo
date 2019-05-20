package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.LinkManDao;
import cn.itcast.domain.Customer;
import cn.itcast.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * OneToManyTest
 * hasee
 * 2018/12/19
 * 9:59
 *
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;
/*----------------------------------客户到联系人的关系---------------------------------------*/
    /*
    *
    * 保存一个客户，保存一个联系人
    *   效果： 客户和联系人作为独立的数据保存到数据库中
    *           问题：联系人的外键为空？
    *           原因： 实体类中没有配置关系
    *           解决： 调用add方法添加；
    *
    * */
    @Test
    @Transactional
    @Rollback(false)
    public void testAdd(){
        //1. 创建一个客户，创建一个联系人
        Customer customer=new Customer();
        customer.setCustName("百度");

        LinkMan linkMan=new LinkMan();
        linkMan.setLkmName("百度员工");

        //上面执行的只是保存操作，没有对值进行添加；
        //因为这个customer.LinkMans是一个set集合，
        // getLinkMans被使用后返回一个linkMans,
        // 所以可以使用集合自身的add方法将其添加到集合内；
        /*
        * 配置了客户到联系人的关系
        *       从客户的角度上： 发送两条Insert语句，发送一条更新语句更新数据库（更新外键）
        *     由于我们配置了客户到联系人的关系： 客户可以对外键进行维护
        *
        * */
        customer.getLinkMans().add(linkMan);


        //2. 调用方法，进行保存操作
        customerDao.save(customer);
        linkManDao.save(linkMan);
        //3. 查询操作


            //3.1. 调用spec动态查询方法查询
        Specification specification=new Specification() {
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Path custName=root.get("custName");
                Predicate one=cb.equal(custName, "百度");
                return one;
            }
        };
        //3.2. 使用查询全部方法查询
        List all=customerDao.findAll(specification);
        for (Object o : all) {
            System.out.println(o);
        }
        List<LinkMan> list=linkManDao.findAll();
        for (LinkMan man : list) {
            System.out.println("likeMan:"+man);
        }
    }


    /*----------------------------------联系人到客户的关系---------------------------------------*/

    @Test
    @Transactional
    @Rollback(false)
    public void TestAdd1(){
        //创建一个客户，一个联系人
        Customer customer=new Customer();
        customer.setCustName("百度");

        LinkMan linkMan=new LinkMan();
        linkMan.setLkmName("小李子是百度员工");

        /*
        * 配置联系人到客户的关系（多对一）
        *   只发送了两条insert语句
        * 由于配置了联系人到客户的映射关系（多对一）
        * */

        linkMan.setCustomer(customer);



        customerDao.save(customer);
        linkManDao.save(linkMan);
    }



/*----------------------------两个关系同时弄---------------------------------------*/
    /*
     * 会有一条多余的update外键的语句
     *      * 由于一的一方可以维护外键，会发送update语句
     *      * 解决此问题： 只需要在一的一方放弃维护权即可；
     *                  * 因为两个都对外键进行了维护，只需要其中一个放弃了维护权，那么就不会再造成性能浪费；
     *
     *
     * */
    @Test
    @Transactional
    @Rollback(false)
    public void TestAdd2(){
        //创建一个客户，一个联系人
        Customer customer=new Customer();
        customer.setCustName("百度");

        LinkMan linkMan=new LinkMan();
        linkMan.setLkmName("小李子是百度员工");

        /*
         * 配置联系人到客户的关系（多对一）
         *   只发送了两条insert语句
         * 由于配置了联系人到客户的映射关系（多对一）
         * */

        linkMan.setCustomer(customer);          //由于配置了多的一方到一方的关联关系（当保存的时候，就已经对外键赋值）
        customer.getLinkMans().add(linkMan);    //由于配置了一的一方到多的一方的关联关系（发送一条update语句）


        customerDao.save(customer);
        linkManDao.save(linkMan);
    }


    /*-----------------------------级联添加---------------------------------*/

    /*
    * 级联添加：保存一个客户的同时，保存客户的所有联系人
    *       需要在操作主体的实体类上，配置casacde属性
    * */

    @Test
    @Transactional      //配置事务
    @Rollback(false)    //不自动回滚
    public void testCascacdeAdd(){
        Customer customer=new Customer();
        customer.setCustName("百度1");

        LinkMan linkMan=new LinkMan();
        linkMan.setLkmName("小李子1");

        /*
        * 1. 这里linkMan是一个对象，给这个对象赋值(添加外键关系)需要使用它的set方法
        * 2. 这里的getLinkMans()方法返回的是一个Set<LinkMan>LinkMans 集合，所以它的赋值是使用该集合自带的add方法；
        * */
        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);


        //保存主体对象，从表也会跟着级联保存；
        customerDao.save(customer);
    }

    /*---------------------------级联删除-----------------------------*/
    /*
    * 级联删除：
    *   * 删除1号客户的同时，删除1号客户的所有联系人
    *
    * */
    @Test
    @Transactional      //配置事务
    @Rollback(false)    //设置不自动回滚
    public void testCascadeRemove(){
        //1. 查询1号客户
        Customer customer= customerDao.findOne(1l);
        //2. 删除1号客户
        customerDao.delete(customer);

    }






}
