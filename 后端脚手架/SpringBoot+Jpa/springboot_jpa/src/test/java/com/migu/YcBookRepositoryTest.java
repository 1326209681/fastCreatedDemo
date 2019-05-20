package com.migu;

import com.migu.domain.YcBook;
import com.migu.domain.YcBookContent;
import com.migu.repository.YcBookContentRepository;
import com.migu.repository.YcBookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * YcBookRepositoryTest
 * hasee
 * 2018/12/20
 * 18:33
 *
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootJpaApplication.class)
public class YcBookRepositoryTest {

    //注入两个Jpa
    @Autowired
    private YcBookRepository ycBookRepository;
    @Autowired
    private YcBookContentRepository ycBookContentRepository;

    //测试查询BookContent
    @Test
    public void test1(){
        List<YcBookContent> list=ycBookContentRepository.findAll();
        System.out.println(list);
    }
    //测试查询所有
    @Test
    public void test2(){
        List<YcBook> all=ycBookRepository.findAll();
        System.out.println(all);
    }

    //查询单个BookContent
    @Test
    public void test3(){
        Optional<YcBookContent> list=ycBookContentRepository.findById(2);
        System.out.println(list.toString());
    }

}
