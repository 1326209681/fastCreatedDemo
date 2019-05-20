package com.liutao.util;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @program: test2
 * @author: 张磊
 * @create: 2019/3/8-9:38
 **/

public class test2 {
    public static void main(String[] args) {
        OutputStream outputStream=null;
        try {
            byte[] verifyImg=VerifyCodeUtil.getVerifyImg();
            FileImageOutputStream imageOutput=new FileImageOutputStream(new File("D://image3.jpg"));
            imageOutput.write(verifyImg, 0, verifyImg.length);//将byte写入硬盘
            imageOutput.close();
            System.out.println();
        } catch (Exception e) {
        }


    }

}