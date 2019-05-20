package com.yuanchunyuyi.service.com.yuanchun.service;

import com.yuanchunyuyi.domain.YcBook;
import com.yuanchunyuyi.domain.YcBookContent;
import com.yuanchunyuyi.domain.ycBookList;
import org.springframework.stereotype.Service;



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
