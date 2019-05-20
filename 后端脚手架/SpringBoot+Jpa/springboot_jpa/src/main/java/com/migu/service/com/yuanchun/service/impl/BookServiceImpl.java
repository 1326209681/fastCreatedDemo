package com.migu.service.com.yuanchun.service.impl;

import com.migu.domain.YcBook;
import com.migu.domain.YcBookContent;
import com.migu.domain.ycBookList;
import com.migu.repository.YcBookContentRepository;
import com.migu.repository.YcBookRepository;
import com.migu.service.com.yuanchun.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
