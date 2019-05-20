package com.migu.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * YcBook
 * hasee
 * 2018/12/20
 * 18:18
 *
 * @Version 1.0
 **/

@Entity
@Table(name = "yc_book")
public class YcBook implements Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)       //配置主键的生成策略
    @Column(name = "yc_book_Id")
    private String id;
    @Column(name = "yc_book_Name")
    private String bookname;
    @Column(name = "yc_book_createtime")
    private String createTime;
    @Column(name = "yc_book_price")
    private String bookprice;

   /* private String name;*/

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

/*
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }
*/

/*    @Override
    public String toString() {
        return "YcBook{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }*/
@Override
public String toString() {
    return "YcBook{" +
            "id='" + id + '\'' +
            ", bookname='" + bookname + '\'' +
            ", bookprice='" + bookprice + '\'' +
            '}';
}
}
