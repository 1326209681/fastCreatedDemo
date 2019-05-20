package cn.itcast.dao;

import cn.itcast.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
* 符合SpringDataJpa的dao层接口规范
*   1. JpaRepository<操作的实体类类型，实体类中主键属性的类型>
*      * 封装了基本的CRUD操作
*
*   2. JpaSpecificationExecutor<操作的实体类类型>
*       * 封装了复杂查询（分页）
* */
public interface CustomerDao extends JpaRepository<Customer,Long> ,JpaSpecificationExecutor<Customer>{


    /*
    * JPQL---------------------------------------JPQL---------------------------------------------------JPQL----------------------------------------------------
    *
    *
    * */
    /*
    *
    * 案例：根据客户名称查询客户
    *       1. 使用jpql的形式查询客户
    *           * jpql:   from Customer where custName=?
    *     配置jpql语句，使用的@Query注解
    *
    *
    * */
    @Query(value = "from Customer where custName=?")
    public Customer findjpql(String custName);





    /*
    * 案例： 根据客户名称和客户id查询客户
    * jpql:from Customer wehre custName=? and custId=?
    *
    *
    * */
    @Query(value = "from Customer where custName = ? and custId = ? ")
    public  Customer findCustNameAndId(String name,Long id);




    /*
     * 使用jpql完成更新操作
     *       案例： 根据id更新，客户的名称
     *               更新4号客户的名称，将名称改为“程序员”
     *    sql: update cst_custtomer set cust_name=? where cust_id=?
     *    jpql: update Customer set custName=? where custId=?
     *
     *   @Query:代表的是进行查询
     *     * 声明此方法是用来进行更新操作
     *   @Modifying
     *      * 当前执行的是一个更新操作
     * */
    @Query(value = "update Customer set custName= ?2 where custId =?1")
    @Modifying
    public void updateCustomer(long custId,String custName);



    /*
    *
    * SQL ---------------------------------------------SQL---------------------------------------SQL----------------------------------------------
    *
    * */
    /*
    * 使用sql形式查询
    *   查询全部的客户
    *   sql: select * from cst_customer;
    *   Query: 配置sql语句
    *       value： sql语句
    *       nativeQuery: 查询方式
    *           true: sql查询
    *           false: jpql查询
    * */
    @Query(value = "select * from cst_customer",nativeQuery = true)
    public List<Object[]> findSql();



    /*
    *
    * 根据用户名进行模糊匹配查询
    * */
    @Query(value = "select * from cst_customer where cust_name like ?1",nativeQuery = true)
    public List<Object[]> findNameBySql(String name);


    /*
    * 方法名称规则查询-----------------------------------方法名称规则查询--------------------------------------方法名称规则查询--------------------------------------方法名称规则查询-------------------------------------------
    *
    *
    * */
    /*
    * 方法名的约定：
    *   findBy:查询
    *       对象中的属性名（首字母大写）： 查询的条件
    *       CustName
    *   findByCustName     -- 根据客户名称查询
    *   在SpringDataJpa的运行阶段
    *               会根据方法名称进行解析   findBy   from xxx(实体类)
    *                       属性名称     where custName=
    *
    *    findBy+属性名称（根据属性名称进行完成匹配的查询=）
    *    findBy + 属性名称+ "查询方式（Like | isnull）"
    *
    *    模糊查询格式： findByCustNameLike
    * */
    public Customer findByCustName(String custName);


    /*
    *
    * 模糊匹配
    * */
    public List<Customer> findByCustNameLike(String custName);



    /*
    * 多条件： 模糊匹配+ 精准查询
    *
    * */
    //使用客户名称模糊匹配和客户所属行业精准匹配的查询
    //如果考虑到有多个结果的话，返回值可以不用Customer对象，而是用这个对象的集合才能接收到所有数据，否则会报错；
    public Customer findByCustNameLikeAndCustIndustry(String custName,String custIndustry);



}
