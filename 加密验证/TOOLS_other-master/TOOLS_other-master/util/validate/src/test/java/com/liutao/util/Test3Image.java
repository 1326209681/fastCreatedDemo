package com.liutao.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Random;

public class Test3Image {
    public static void main(String[] args) {
        Test3Image test = new Test3Image();
        try {
          //  test.testCode();
            test.testImg();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 /*   private static void testCode() throws IOException {
        byte[] newImages = VerifyCodeUtil.getVerifyImg();
        FileOutputStream fout = new FileOutputStream("D://old.jpg");
        fout.write(newImages);
        fout.close();

    }*/

    public void testImg() throws Exception {
        Map<String, byte[]> pictureMap;
        File templateFile;  //模板图片
        File targetFile;  //
      /*  Random random = new Random();
        int templateNo = random.nextInt(4) + 1;
        int targetNo = random.nextInt(20) + 1;
*/
        InputStream stream = getClass().getClassLoader().getResourceAsStream("static/templates/2.png");
       // InputStream stream = getClass().getClassLoader().getResourceAsStream("static/templates/" + templateNo + ".png");
     //   templateFile = new File(templateNo + ".png");
        System.out.println("stream"+stream);
        templateFile = new File( "static/templates/21.jpg");

        FileUtils.copyInputStreamToFile(stream, templateFile);

        //传入哪张图，哪个图就会被变为底图；
        stream = getClass().getClassLoader().getResourceAsStream("static/targets/" + "2" + ".jpg");
       targetFile = new File( "111111.jpg");
        FileUtils.copyInputStreamToFile(stream, targetFile);
        pictureMap = VerifyImageUtil.pictureTemplatesCut(templateFile, targetFile, "png", "jpg");
        byte[] oriCopyImages = pictureMap.get("oriCopyImage");
        byte[] newImages = pictureMap.get("newImage");



        //这是底图
        FileOutputStream fout = new FileOutputStream("D://底图.jpg");

        //将字节写入文件
        try {
            fout.write(oriCopyImages);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fout.close();


        //这是滑块图
        FileOutputStream newImageFout = new FileOutputStream("D://滑块图.jpg");
        //将字节写入文件
        newImageFout.write(newImages);
        newImageFout.close();
    }
}
