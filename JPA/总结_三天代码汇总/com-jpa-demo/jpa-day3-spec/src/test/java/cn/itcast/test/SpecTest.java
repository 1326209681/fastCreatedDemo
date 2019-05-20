package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.security.PublicKey;
import java.util.List;

/**
 * SpecTest
 * hasee
 * 2018/12/17
 * 20:26
 *
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {

    @Autowired
    private CustomerDao customerDao;




    /*
    * 根据查询条件查询单个对象
    *
    * */

    @Test
    public void testSpec(){
        /*
         * 匿名内部类
         *
         * 自定义查询条件
         *   1. 实现Specification接口 （提供泛型：查询的对象类型）
         *   2. 实现toPredicate方法（构造查询条件）
         *   3. 需要借助方法参数中的两个参数{
         *       root: 获取需要查询的对象属性
         *       CriteriaBuilder: 构造查询条件的，内部封装了很多的查询条件（模糊匹配，精准匹配）
         *    4. 案例：
         *          * 根据客户条件查询查询客户名为传智博客的客户
         *              * 查询条件
         *                  1. 查询方式
         *                      cb对象
         *                  2. 比较的属性名称
         *                      root对象
         *   }
         * */
        Specification<Customer> spec=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1. 获取比较的属性
                Path<Object> custName=root.get("custName");
                //2. 构造查询条件:     select * from cst_customer where cust_name='暗余大神'
                    /*
                    * 第一个参数：需要比较的属性（path对象）
                    * 第二个参数： 当前需要比较的取值
                    *
                    * */
                Predicate custom=cb.equal(custName, "暗余大神");


                return custom;
            }
        };
        Customer customer=customerDao.findOne(spec);
        System.out.println(customer);
    }


    /*
    * 多条件查询
    *   案例： 根据客户名和客户所属行业查询
    *
    * */
    @Test
    public void testSpec1(){
        /*
        * root: 获取属性
        *       客户名
        *       所属行业
        *
        *  cb： 构造查询
        *       1. 构造客户名的精准匹配查询
        *       2. 构造所属行业的精准匹配查询
        *       3. 将以上两个查询联系起来
        *
        * */
        Specification<Customer>spec=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName=root.get("custName");         //客户名
                Path<Object> custIndustry=root.get("custIndustry");     //所属行业
                //构造查询
                //1. 构造客户名的精准匹配查询
                Predicate p1=cb.equal(custName, "暗余大神");
                //2. 构造所属行业的精准匹配查询
                Predicate p2=cb.equal(custIndustry,"职业战队队长");
                //3. 将多个查询条件组合到一起
                        /*
                        * 关系有两种：
                        *   1. 与：  满足条件一并且满足条件二         cb.and();
                        *   2. 或 ：  满足条件一或满足条件二          cb.or();
                        *   注意：我们这里是必须两个条件都满足，所以是与的关系；
                        * */
                Predicate and=cb.and(p1, p2);
                return and;
            }
        };

        Customer one=customerDao.findOne(spec);
        System.out.println(one);

    };




    /*
    * 案例：完成根据客户名称的模糊匹配，返回客户列表
    *   equal: 直接得到path对象（属性），然后进行比较即可
    *   gt,lt,ge,le,like: 得到path对象，根据path指定比较的参数类型，再去进行比较
    *   指定参数类型： path.as(类型的字节码对象)
    *
    * */

    @Test
    public  void testSpec3(){

        //构造查询条件
      Specification<Customer> spec=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1. 查询属性： 客户名
                Path<Object> custName=root.get("custName");
                //2. 查询方式： 模糊匹配
                Predicate one=cb.like(custName.as(String.class), "暗%");
                return one;
            }
        };

        //查询

        List<Customer> list=customerDao.findAll(spec);
        for (Customer customer : list) {
            System.out.println(customer);
        }

    }



        /*
        * 排序查询+模糊查询
        *
        *
        * */

        /*
         * 案例：完成根据客户名称的模糊匹配，返回客户列表，以id值倒序排序显示结果
         *   equal: 直接得到path对象（属性），然后进行比较即可
         *   gt,lt,ge,le,like: 得到path对象，根据path指定比较的参数类型，再去进行比较
         *   指定参数类型： path.as(类型的字节码对象)
         *
         * */

        @Test
        public  void testSpec4(){

            //构造查询条件
            Specification<Customer> spec=new Specification<Customer>() {
                public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    //1. 查询属性： 客户名
                    Path<Object> custName=root.get("custName");
                    //2. 查询方式： 模糊匹配
                    Predicate one=cb.like(custName.as(String.class), "暗%");
                    return one;
                }
            };

            Sort sort=new Sort(Sort.Direction.DESC,"custId");
            List<Customer> list=customerDao.findAll(spec, sort);
            for (Customer customer : list) {
                System.out.println(customer);
            }

        }



        /*
        *
        * 分页查询
        *
        *   Specification: 查询条件
        *   Pageable:   分页参数
        *       分页参数：查询的页码，每页查询的条数
        *           findAll(Specification,Pageable):        带有条件的分页
        *           findAll(Pageable):                  没有条件的分页，就是获取所有结果并分页
        *   返回： Page(SpringDataJpa为我们封装好的pageBean对象，数据列表，共条数)
        * */
        @Test
        public void testSpec5(){
            Specification spec=null;

            /*
            * 创建PageRequest的过程中，需要调用他的构造方法传入两个参数
            * 第一个参数：当前查询的页数（从0开始）
            * 第二个参数： 每页查询的数量
            * */
            //PageRequest对象是Pageable接口的实现类
            Pageable pageable=new PageRequest(0,2);
            Page page=customerDao.findAll(spec, pageable);
            System.out.println(page.getContent());          //得到数据集合列表
            System.out.println(page.getTotalElements());        //得到总条数
            System.out.println(page.getTotalPages());       //得到总页数

            //分页对象在查询的时候，首先会获取所有数据封装到page对象中，调用getContent会显示分页后的那页数据
            //总条数会显示所有数据条数，总页数是根据分页的结果来显示的；如果4条数据分2页则总页数为2；
        }










}
