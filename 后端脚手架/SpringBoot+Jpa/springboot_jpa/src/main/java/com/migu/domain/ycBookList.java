package com.migu.domain;

import java.io.Serializable;
import java.util.List;

/**
 * ycBookList
 * hasee
 * 2019/1/22
 * 10:14
 *
 * @Version 1.0
 **/
public class ycBookList implements Serializable{

    private String id;
    private String bookname;
    private String createTime;
    private String bookprice;
    private List<YcBookContent> contentList;

    @Override
    public String toString() {
        return "ycBookList{" +
                "id='" + id + '\'' +
                ", bookname='" + bookname + '\'' +
                ", createTime='" + createTime + '\'' +
                ", bookprice='" + bookprice + '\'' +
                ", contentList=" + contentList +
                ", ContentList=" + ContentList +
                '}';
    }

    public List<YcBookContent> getContentList() {
        return contentList;
    }


    public void setContentList(List<YcBookContent> contentList) {
        this.contentList=contentList;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname=bookname;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime=createTime;
    }

    public String getBookprice() {
        return bookprice;
    }

    public void setBookprice(String bookprice) {
        this.bookprice=bookprice;
    }



    private List<String> ContentList;
}
