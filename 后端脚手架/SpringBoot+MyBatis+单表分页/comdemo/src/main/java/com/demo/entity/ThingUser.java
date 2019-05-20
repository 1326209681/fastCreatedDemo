package com.demo.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: ThingUser
 * @author: 张磊
 * @create: 2019/5/20-10:53
 **/
@Data
@ToString
public class ThingUser implements Serializable{

    private String thingId;
    private String thing;
    private String parentId;
}
