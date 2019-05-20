package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * SqlTest
 * hasee
 * 2018/12/17
 * 15:46
 *
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)         //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SqlTest {

    @Autowired
    private CustomerDao customerDao;

    /*
    *
    * 使用sql查询全部数据
    *
    * */
    @Test
    public void testFindSql(){
        List<Object[]> sql=customerDao.findSql();
        for (Object[] obj : sql) {
            System.out.println(Arrays.toString(obj));
        }
    }



    /*
    *
    * 模糊查询
    * */
    @Test
    public void testFindNameBySql(){
        List<Object[]> list=customerDao.findNameBySql("暗%");
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }


}
