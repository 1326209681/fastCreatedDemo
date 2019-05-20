package com.yuanchunyuyi.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * YcBookContent
 * hasee
 * 2019/1/22
 * 9:55
 *
 * @Version 1.0
 **/
@Entity
@Table(name = "yc_book_content")
public class YcBookContent implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)       //配置主键的生成策略
    @Column(name = "yc_book_content_Id")
    private Integer ContentId;
    @Column(name = "yc_book_content")
    private String Content;

    @Override
    public String toString() {
        return "YcBookContent{" +
                "ContentId='" + ContentId + '\'' +
                ", Content='" + Content + '\'' +
                '}';
    }

    public Integer getContentId() {
        return ContentId;
    }

    public void setContentId(Integer contentId) {
        ContentId=contentId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content=content;
    }
}
