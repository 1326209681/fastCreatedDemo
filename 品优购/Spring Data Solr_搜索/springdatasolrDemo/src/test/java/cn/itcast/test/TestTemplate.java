package cn.itcast.test;

import cn.itcast.pojo.TbItem;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * TestTemplate
 * hasee
 * 2018/11/28
 * 16:06
 *
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-solr.xml")
public class TestTemplate {

    @Autowired
    private SolrTemplate solrTemplate;


        /*
        * 增加修改，
        * 	* 它的增加和修改是一样的，以id值为基准，
                1. 如果id值一样，但是其他的不一样，则修改；
                2. 如果id值不一样，但是其他的一样也是增加；
        * */
    @Test
    public void add() {
        TbItem item=new TbItem();
        item.setId(1L);
        item.setTitle("华为Mate10");
        item.setCategory("手机");
        item.setBrand("华为");
        item.setSeller("华为官方旗舰店");
        item.setGoodsId(1L);
        item.setPrice(new BigDecimal(3000.01));
        solrTemplate.saveBean(item);
        solrTemplate.commit();
    }

    /*
    * 按照id值查询
    *
    * */
    @Test
    public void findById(){
        TbItem item=solrTemplate.getById(1L, TbItem.class);
        System.out.println(item.getTitle());
    }

    /*
    * 按照id值删除
    *
    * */
    @Test
    public void deleteById(){
        UpdateResponse item=solrTemplate.deleteById("1");
        System.out.println(item);
        solrTemplate.commit();
    }

    /*
    * 批量插入数据：
    * 循环插入100条数据
    * 后面使用分页查询需要用到
    * */
    @Test
    public void testAddList(){
        List list=new ArrayList();
        for (int i=0; i < 100; i++) {
            TbItem item=new TbItem();
            item.setId(i+1L);
            item.setTitle("华为METE"+i);
            item.setCategory("手机");
            item.setBrand("华为"+i);
            item.setSeller("华为旗舰店");
            item.setGoodsId(10L);
            item.setPrice(new BigDecimal(3000.01+i));
            list.add(item);
        }
        solrTemplate.saveBeans(list);
        solrTemplate.commit();
    }


    /*
    *
    * 分页查询数据
    *
    * */
    @Test
    public void testPageQuery(){
       Query query=new SimpleQuery("*:*");
       query.setOffset(0);  //开始索引
       query.setRows(10);       //结束索引
        ScoredPage<TbItem> page=solrTemplate.queryForPage(query, TbItem.class);
        for (TbItem item : page.getContent()) {
            System.out.println(item.getTitle()+"  "+item.getPrice()+"  "+item.getBrand());
        }
        System.out.println("总记录数"+page.getTotalElements());
        System.out.println("总页数"+page.getTotalPages());
    }


    /*
    * 条件查询数据
    *
    * */
    @Test
    public void testPageQueryByTerm(){
        Query query=new SimpleQuery("*:*");
        Criteria criteria=new Criteria("item_category").is("手机");           //这里也可以将is替换为contains,含义一样
        criteria=criteria.and("item_brand").contains("2");
        query.addCriteria(criteria);
        query.setOffset(0);  //开始索引
        query.setRows(10);       //结束索引
        ScoredPage<TbItem> page=solrTemplate.queryForPage(query,TbItem.class);
        for (TbItem item : page.getContent()) {
            System.out.println(item.getTitle()+"  "+item.getPrice()+"  "+item.getBrand());
        }
        System.out.println("总记录数"+page.getTotalElements());
        System.out.println("总页数"+page.getTotalPages());
    }


    /*
    * 删除全部
    * */
    @Test
    public void deleteAll(){

         //这里的构造里必须带条件，如果构造里面没有带条件，想删除的话，应该在删除之前给query添加条件，否则删除不成功
        Query query=new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
    }
}
