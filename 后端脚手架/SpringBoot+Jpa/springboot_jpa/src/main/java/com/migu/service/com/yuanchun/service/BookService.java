package com.migu.service.com.yuanchun.service;

import com.migu.domain.YcBook;
import com.migu.domain.YcBookContent;
import com.migu.domain.ycBookList;


public interface BookService {
    /*
    * 返回全部列表
    *
    * */
   public ycBookList findBookList();

   /*
   * 返回主表
   *
   * */
    public YcBook findBookById();

    YcBookContent findBookContentById(Integer id);
}
