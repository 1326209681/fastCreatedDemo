package com.yuanchunyuyi.service.com.yuanchun.service.impl;

import com.yuanchunyuyi.domain.YcBook;
import com.yuanchunyuyi.domain.YcBookContent;
import com.yuanchunyuyi.domain.ycBookList;
import com.yuanchunyuyi.repository.YcBookContentRepository;
import com.yuanchunyuyi.repository.YcBookRepository;
import com.yuanchunyuyi.service.com.yuanchun.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 * BookServiceImpl
 * hasee
 * 2019/1/22
 * 10:13
 *
 * @Version 1.0
 **/
@Service("BookService")
public class BookServiceImpl implements BookService {
    @Autowired
    private YcBookRepository ycBookRepository;
    @Autowired
    private YcBookContentRepository ycBookContentRepository;
    @Override
    public ycBookList findBookList() {
        //创建一个新的ycBookList
        ycBookList ycBookList=new ycBookList();

        //调用Jpa的findAll方法，查询基础数据；
        List<YcBook> booklist=ycBookRepository.findAll();

        //将值封装进去
        ycBookList.setBookname(booklist.get(0).getBookname());
        ycBookList.setBookprice(booklist.get(0).getBookprice());
        ycBookList.setCreateTime(booklist.get(0).getCreateTime());

        //调用Jpa的另一个findAll方法，查询该图书的详细数据；
        List<YcBookContent> list=ycBookContentRepository.findAll();
        ycBookList.setContentList(list);
        return ycBookList;
    }

    @Override
    public YcBook findBookById() {
        return ycBookRepository.findAll().get(0);
    }

    @Override
    public YcBookContent findBookContentById(Integer id) {
        Optional<YcBookContent> list=ycBookContentRepository.findById(id);
        YcBookContent bookContent=list.get();
        return bookContent;
        /*return ycBookContentRepository.findOne(id);*/
    }


}
