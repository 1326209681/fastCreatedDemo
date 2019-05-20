package com.demo.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: Teachplan
 * @author: 张磊
 * @create: 2019/4/1-23:17
 **/
@Data
@ToString
public class Teachplan implements Serializable{
    private String id;
    private String username;
}
