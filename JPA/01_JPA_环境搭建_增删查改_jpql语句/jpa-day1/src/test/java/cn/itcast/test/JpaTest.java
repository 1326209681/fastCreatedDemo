package cn.itcast.test;

import cn.itcast.domin.Customer;
import cn.itcast.util.JpaUtils;
import org.junit.Test;

import javax.persistence.*;

/**
 * JpaTest
 * hasee
 * 2018/12/16
 * 15:01
 *
 * @Version 1.0
 **/

public class JpaTest {
    /*
    *
    * 测试jpa的保存
    * 案例：保存一个客户到数据库中；
    * Jpa的操作步骤
    *   1. 加载配置文件创建工厂（实体管理类工厂）对象
    *   2. 通过实体管理类工厂获取实体管理器
    *   3. 获取事务对象，开启事务
    *   4. 完成增删改查操作
    *   5. 提交事务（回滚事务）
    *   6. 释放资源
    * */

    @Test
    public void testSave(){
        //1. 加载配置文件创建工厂（实体管理器工厂）对象
//        EntityManagerFactory factory=Persistence.createEntityManagerFactory("myJpa");
//        //2. 通过实体管理器工厂获取实体管理器
      EntityManager em=JpaUtils.getEntityManager();     //通过工具类创建实体管理器
        //3. 获取事务对象，开启事务；
        EntityTransaction tx=em.getTransaction();       //获取事务对象
        tx.begin();         //开启事务
        //4. 完成增删改查操作：保存一个数据到数据库中
            //4.1 创建一个实体类
        Customer customer=new Customer();
        customer.setCustName("暗余");
        customer.setAddress("成都");
        customer.setCustPhone("15982131554");
        customer.setCustLevel("5");
        // 4.2  保存操作
        em.persist(customer);
        //5.提交事务
        tx.commit();
        //6. 释放资源
        em.clear();
        //通过工具类创建实体类管理器后，factory不必关闭资源，因为它是一个公共的factory,后面其他线程如果需要使用此factory就可以直接使用了；
       // factory.close();
    }


    //根据id查询客户
    /*
    * 使用id查询客户
    * 使用find方法查询：
    *   1. 查询的对象就是当前客户对象本身
    *   2. 在调用find方法的时候，就会发送sql语句查询数据库
    * 特点：
    *   立即加载
    *       1. 它是在调用find方法本身，而且是在调用find方法的时候就已经完成查询
    *       2. 使用它会浪费内存效率，所以我们一般使用getReference方法进行查询；
    * */

    @Test
    public void testFind(){
        //1.通过工具类获取EntityManager
        EntityManager entityManager=JpaUtils.getEntityManager();
        //2. 开启事务
        EntityTransaction transaction=entityManager.getTransaction();
        transaction.begin();
        //3. 增删改查
        /*
        * find: 根据id查询数据
        *       class:查询数据的结果需要包装的实体类类型的字节码
        *       id:查询的主键的取值   (l是必须要带上的，否则会报错)
        *
        * */
        Customer customer=entityManager.find(Customer.class,1l);
        System.out.println(customer);
        //4. 提交事务
        transaction.commit();
        //5. 释放资源
        entityManager.close();

    }

        /*
        * 根据id查询用户
        *   getReference方法
        *   1. 获取的对象是一个动态代理对象
        *   2. 调用getReference方法不会立即发送sql语句查询数据库
        *       * 当调用查询结果对象的时候，不会发送查询的sql语句，什么时候用，什么时候发送sql语句查询数据库
        *   查询的特点：
        *       延迟加载（懒加载）
        *       1. 什么时候用，什么时候才会加载
        *       2. 得到的是一个动态代理对象
        * */


    @Test
    public void testReference(){
        //1.通过工具类获取EntityManager
        EntityManager entityManager=JpaUtils.getEntityManager();
        //2. 开启事务
        EntityTransaction transaction=entityManager.getTransaction();
        transaction.begin();
        //3. 增删改查
        /*
         * find: 根据id查询数据
         *       class:查询数据的结果需要包装的实体类类型的字节码
         *       id:查询的主键的取值   (l是必须要带上的，否则会报错)
         *
         * */
        Customer customer=entityManager.getReference(Customer.class,1l);
        System.out.println(customer);
        //4. 提交事务
        transaction.commit();
        //5. 释放资源
        entityManager.close();

    }




    /*
    * 删除
    * */


    @Test
    public void testRemove(){
        //1.通过工具类获取EntityManager
        EntityManager entityManager=JpaUtils.getEntityManager();
        //2. 开启事务
        EntityTransaction transaction=entityManager.getTransaction();
        transaction.begin();
        //3. 增删改查-- 删除客户

            //3.1 根据客户id查询用户
        Customer customer=entityManager.find(Customer.class, 1l);
            //3.2 根据客户查询得到的对象删除用户
        entityManager.remove(customer);
        //4. 提交事务
        transaction.commit();
        //5. 释放资源
        entityManager.close();

    }



    /*
    *
    * 更新
    *   merge(Object)
    * */
    @Test
    public  void testUpdate(){
        //1.通过工具类获取EntityManager
        EntityManager entityManager=JpaUtils.getEntityManager();
        //2. 开启事务
        EntityTransaction transaction=entityManager.getTransaction();
        transaction.begin();
        //3. 增删改查-- 更新操作
            //i 查询客户
        Customer customer=entityManager.getReference(Customer.class, 2l);
        customer.setCustName("暗余大神");
        //ii 更新客户
        entityManager.merge(customer);


        //4. 提交事务
        transaction.commit();
        //5. 释放资源
        entityManager.close();



    }

}
