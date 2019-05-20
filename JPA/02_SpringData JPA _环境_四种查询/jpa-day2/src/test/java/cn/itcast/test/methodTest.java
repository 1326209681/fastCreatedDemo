package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * methodTest
 * hasee
 * 2018/12/17
 * 16:09
 *
 * @Version 1.0
 **/

/*
*
* 方法命名规则查询
* */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class methodTest {

    @Autowired
    private CustomerDao customerDao;



    /*
    *
    * 它的接口方法名为：findBy+属性名（首字母大写）
    * 使用此种方式查询可以不用在接口写@Query等...
    * */
    @Test
    public void testFindCustName(){
        Customer customer=customerDao.findByCustName("暗余大神");
        System.out.println(customer);
    }



    /*
    *
    * 模糊查询
    * */
    @Test
    public void testFindCustNameLike(){
        List<Customer> list=customerDao.findByCustNameLike("暗余%");
        for (Customer customer : list) {
            System.out.println(customer);
        }

    }

    /*
    *
    * 模糊查询+精准查询
    * */
    @Test
    public void testfindByCustNameLikeAndCustIndustry(){
        Customer customer=customerDao.findByCustNameLikeAndCustIndustry("暗余%", "荣耀王者100星");
        System.out.println(customer);

    }

}
