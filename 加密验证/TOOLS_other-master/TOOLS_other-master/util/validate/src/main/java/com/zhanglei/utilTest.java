package com.zhanglei;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Random;

/**
 * @program: utilTest
 * @author: 张磊
 * @create: 2019/3/10-23:36
 **/

public class utilTest {

    public static void main(String[] args) {
        util test=new util();
        Random rand=new Random();       //定义随机数
        int min =90;
        int max=230;
        int min2=10;
        int max2=100;

        Integer rx=rand.nextInt(max-min)+min;       //130~210的x轴
        Integer ry=rand.nextInt(max2-min2)+min2;    //10~100的y轴

        try{
            BufferedImage image=test.Images();      //调用连接本地底图的方法；
            BufferedImage checkImgMin=test.getMarkImage(image,rx,ry,55,55);     //这张原图的长宽+抠图区的位置+抠出来的图长宽   这里是抠出来的小图片
            BufferedImage checkImg=test.cutByTemplate(image,test.getCutAreaData(310,172,rx,ry,55,55));
            BufferedImage frameMin=test.imagesFrameMin(checkImgMin);        //为小图片添加红色的小边框
            BufferedImage frame=test.imagesFrame(checkImg,rx,ry,55 ,55);
            BufferedImage dest=test.simpleBlur(frame,null);     //图片经过高斯模糊
            BufferedImage destMin=test.simpleBlur(frameMin,null);       //图片经过高斯模糊；
            byte[] ImageWriter=test.compressPictures(dest);     //压缩图片转jpg
            byte[] ImageWriterMin=test.compressPictures(destMin);
            ByteArrayInputStream in=new ByteArrayInputStream(ImageWriter);      //将b作为输入流
            ByteArrayInputStream InMin=new ByteArrayInputStream(ImageWriterMin);        //将b作为输入流
            BufferedImage dd=ImageIO.read(in);      //转换类型
            BufferedImage ddMin=ImageIO.read(in);       //转换类型
            //String comp=test.imageToBase64(dd,"jpg");         //调用  图片转64位字符串的方法  返回给前端用
            //String compMin=test.imageToBase64(ddMin,"png");       //调用  图片转64位字符串的方法  返回给前端用
            ImageIO.write(dd,"jpg",new File("d:\\test.png"));
            ImageIO.write(ddMin,"jpg",new File("d:\\test1.png"));

            //输出图片到本地D盘下




        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
