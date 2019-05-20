package cn.itcast.test;

import cn.itcast.util.JpaUtils;
import org.junit.Test;
import org.omg.IOP.ENCODING_CDR_ENCAPS;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * JpqlTest
 * hasee
 * 2018/12/16
 * 20:05
 *
 * @Version 1.0
 **/
/*
* 测试jpql查询
*   1. 创建query查询对象
*   2. 对参数进行赋值
*   3. 查询，并得到返回结果
*
* */
public class JpqlTest {






    /*
    *
    * 查询全部：
    *   jpql:from cn.itcast.domain.Customer
    *   sql: SELECT * FROM cst_customer
    * */

    @Test
    public void testFindAll(){
        //1. 获取entityManager对象
        EntityManager em=JpaUtils.getEntityManager();
        //2.1获取事务
        EntityTransaction tx=em.getTransaction();
        //2.2开启事务
        tx.begin();
        //3. 查询全部
            // 全限定名方式：String jpq1="from cn.itcast.domin.Customer";
            //支持简写
        String jpq1="from Customer";
        //4. 创建Query查询对象，query对象才是执行jpql的对象；
        Query query=em.createQuery(jpq1);
        //5. 发送查询并封装结果集
        List list=query.getResultList();
        for (Object obj : list) {
            System.out.println(obj);
        }
        //6. 提交事务
        tx.commit();
        //7.释放资源
        em.close();
    }



    /*
    * 倒序DESC
    *
    * 倒序查询
    * sql：SELECT * FROM cst_customer ORDER BY cust_id DESC
    * jpql: from Customer order by custId
    *
    * */
    @Test
    public  void testOrders(){
        //1. 获取entityManager对象
        EntityManager em=JpaUtils.getEntityManager();
        //2. 开启事务
        EntityTransaction transaction=em.getTransaction();
        transaction.begin();
        //3. 查询事务
        String jpq1="from Customer order by custId";
        Query query=em.createQuery(jpq1);       //创建Query查询对象，query对象才是执行jpq1的对象
        // 发送查询，并封装结果集
        List list=query.getResultList();

        for (Object obj : list) {
            System.out.println(obj);
        }

        //4. 提交事务
        transaction.commit();

        //5. 释放资源
        em.close();


    }

    /*
    * 统计Count
    *
    * 使用jpql查询，统计客户的总数
    *       sql: SELECT COUNT(cust_id) FROM cst_customer
    *       jpql: select count(sustId) from Customer
    *
    * */
    @Test
    public  void testCount(){
        //1. 获取entityManager对象
        EntityManager em=JpaUtils.getEntityManager();
        //2. 开启事务
        EntityTransaction transaction=em.getTransaction();
        transaction.begin();
        //3. 查询
            //3.1 根据jpql语句创建Query查询对象
        String jpql="select count(custId) from Customer";
        Query query=em.createQuery(jpql);
            //3.2对参数赋值
            //3.3 发送查询并封装结果

            /*
            * getResultList:直接将查询结果封装为list集合
            * getSingleResult: 得到唯一的结果集
            *
            * */
        Object result=query.getSingleResult();
        System.out.println(result);

        //4. 提交事务
        transaction.commit();
        //5. 释放资源
        em.close();

    }

    /*
    * 分页查询Limit
    *
    * sql: select * from cst_customer limit 0,2
    * jqpl: from Customer
    *
    *
    *
    * */

    @Test
    public  void testPaged(){
        //1. 获取entityManager 对象
        EntityManager em=JpaUtils.getEntityManager();
        //2. 开启事务
        EntityTransaction transaction=em.getTransaction();
        transaction.begin();
        //3. 查询
            //3.1 根据jpql语句查询创建Query查询对象
            String jpql="from Customer";
            Query query=em.createQuery(jpql);
            //3.2 对参数赋值  -- 分页参数
                //起始索引
                query.setFirstResult(0);
                //每页查询的条数
                  query.setMaxResults(2);
            //3.3 发送查询，获取结果
        List list=query.getResultList();
        for (Object obj : list) {
            System.out.println(obj);
        }
        //4. 提交事务
        transaction.commit();
        //5. 关闭资源
        em.close();
    }


        /*
        *
        * 条件查询
        *   案例：查询客户名称以：“暗”开头的用户
        *   sql:select * from cst_customer where cust_name LIKE ?
        *   jpql: form Customer where custName like ?
        *
        * */


     @Test
    public void testCondition(){
         //1. 获取entityManager 对象
         EntityManager entityManager=JpaUtils.getEntityManager();
         //2. 开启事务
         EntityTransaction transaction=entityManager.getTransaction();
         transaction.begin();
         //3. 查询
            //3.1 根据jpql语句创建Query查询对象
            String jpql="from Customer where custName like ? ";
            Query query=entityManager.createQuery(jpql);
            //3.2 对参数赋值
            query.setParameter(1,"%暗%");
            /*
            * getResultList: 直接将查询结果封装为list集合
            * getSingleResult: 得到唯一的结果集
            *
            * */
         List list=query.getResultList();
         for (Object obj : list) {
             System.out.println(obj);
         }
        // 4. 提交事务
         transaction.commit();
         //5. 关闭资源
         entityManager.close();
     }

}
