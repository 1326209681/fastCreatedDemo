package com.migu.controller.com.music.controller;

import com.migu.domain.YcBook;
import com.migu.domain.YcBookContent;
import com.migu.domain.ycBookList;
import com.migu.service.com.yuanchun.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * BookController
 * hasee
 * 2019/1/22
 * 10:29
 *
 * @Version 1.0
 **/
@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    /*
    *
    * 查询所有数据并封装到一个对象中以JSON返回
    * */
    @RequestMapping("/findBook.do")
    @ResponseBody
    public ycBookList findBookList(){
        ycBookList ycBookList=bookService.findBookList();
        System.out.println(ycBookList);
        return ycBookList;
    }

    /*
    * 查询book的主表
    *
    * */
    @RequestMapping("/findBookById.do")
    @ResponseBody
    public YcBook findBookById(){
        System.out.println("我被访问了");
        return bookService.findBookById();
    }

    /*
    * 查询内容
    *
    * */
    @RequestMapping("/findBookContent.do")
    @ResponseBody
    public YcBookContent findBookContentById(Integer id){
        return bookService.findBookContentById(id);
    }
}
