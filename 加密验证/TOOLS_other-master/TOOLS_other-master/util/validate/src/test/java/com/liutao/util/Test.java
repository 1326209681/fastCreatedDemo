package com.liutao.util;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Map;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        try {
            test.testCode();
            test.testImg();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testCode() throws IOException {
        byte[] newImages = VerifyCodeUtil.getVerifyImg();
        FileOutputStream fout = new FileOutputStream("D://old.jpg");
        fout.write(newImages);
        fout.close();

    }

    public void testImg() throws Exception {
        Map<String, byte[]> pictureMap;
        File templateFile;  //模板图片
        File targetFile;  //
        Random random = new Random();
        int templateNo = random.nextInt(4) + 1;
        int targetNo = random.nextInt(20) + 1;

        InputStream stream = getClass().getClassLoader().getResourceAsStream("static/templates/" + templateNo + ".png");
        templateFile = new File(templateNo + ".png");
        FileUtils.copyInputStreamToFile(stream, templateFile);

        stream = getClass().getClassLoader().getResourceAsStream("static/targets/" + targetNo + ".jpg");
        targetFile = new File(targetNo + ".jpg");
        FileUtils.copyInputStreamToFile(stream, targetFile);
        pictureMap = VerifyImageUtil.pictureTemplatesCut(templateFile, targetFile, "png", "jpg");
        byte[] oriCopyImages = pictureMap.get("oriCopyImage");
        byte[] newImages = pictureMap.get("newImage");

        FileOutputStream fout = new FileOutputStream("D:\\桌面\\Desktop\\滑动模块\\TOOLS_other-master\\TOOLS_other-master\\util\\validate\\src\\main\\resources\\static\\targets\\21.jpg");
        //将字节写入文件
        try {
            fout.write(oriCopyImages);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fout.close();

        FileOutputStream newImageFout = new FileOutputStream("D://newImage2.png");
        //将字节写入文件
        newImageFout.write(newImages);
        newImageFout.close();
    }
}
