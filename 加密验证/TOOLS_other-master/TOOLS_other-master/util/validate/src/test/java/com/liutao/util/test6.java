package com.liutao.util;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: test6
 * @author: 张磊
 * @create: 2019/3/8-14:31
 **/

public class test6 {
    public static void main(String[] args) {
        ArrayList list=new ArrayList();
        Collections.addAll(list,"a","b","c","d");
        String s="";
        for (Object one : list) {
            s+=one;
        }
        System.out.println(s);
    }


}
