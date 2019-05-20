package cn.itcast.pojo;

import lombok.Data;

import java.util.Date;

/**
 * User
 * hasee
 * 2019/1/26
 * 14:36
 *
 * @Version 1.0
 **/
@Data
public class User {
    // id
    private Long id;
    // 用户名
    private String userName;
    // 密码
    private String password;
    // 姓名
    private String name;
    // 年龄
    private Integer age;
    //性别，  1男性，  2 女性
    private Integer sex;
    //出生日期
    private Date birthday;
    //创建时间
    private Date created;
    //更新时间
    private Date updated;
    // 备注
    private String note;
    //get,set省略
}
