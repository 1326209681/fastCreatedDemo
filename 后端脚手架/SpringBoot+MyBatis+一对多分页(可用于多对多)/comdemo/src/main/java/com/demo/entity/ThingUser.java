package com.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: ThingUser
 * @author: 张磊
 * @create: 2019/4/4-0:13
 **/
@Data
public class ThingUser implements Serializable{
    private String thingId;
    private String  thing;

  /*  private String parentId;
    private Teachplan teachplan;*/
}
