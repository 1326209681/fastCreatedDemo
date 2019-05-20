package com.zhanglei;

import sun.misc.BASE64Encoder;

import javax.imageio.*;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Iterator;
import java.util.Random;

/**
 * @program: util
 * @author: 张磊
 * @create: 2019/3/10-22:36
 **/

public class util {


    /**
     * 功能描述：第一步，随机读写一张用于做验证码的图片
     *
     * @param
     * @return java.awt.image.BufferedImage
     ***/
    public BufferedImage Images() throws Exception {
      //  String path="D:/桌面/Desktop/滑动模块/TOOLS_other-master/TOOLS_other-master/util/3image.jpg";     //图片路径
        String path="D:\\桌面\\Desktop\\滑动模块\\TOOLS_other-master\\TOOLS_other-master\\util\\validate\\src\\main\\resources\\static\\targets\\2.jpg";     //图片路径
     //   Random rand=new Random();       //定义随机数
     //   Integer r=rand.nextInt(Integer.valueOf("6"));       //获得随机下标，如果改位置的图片总数为6,为了方便随机获取图片，图片名字为1 6.png
       // path=path + "/" + r.toString() + "png";       //拼接路径

        //先固定取出图
        BufferedImage image=ImageIO.read(new FileInputStream(path));
        return image;
    }


    /**
     * 功能描述：第二步： 抠图方块裁剪，得到一个用来滑动的小方块
     * 对图片裁剪，并把裁剪后的图片返回
     *
     * @param image, x, y, length, width
     * @return java.awt.image.BufferedImage
     ***/
    public BufferedImage getMarkImage(BufferedImage image, int x, int y, int length, int width) throws Exception {
        InputStream is=null;
        ImageInputStream iis=null;
        try {
            ByteArrayOutputStream os=new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", os);
            is=new ByteArrayInputStream(os.toByteArray());
            Iterator<ImageReader> it=ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader=it.next();
            //获取图片流
            iis=ImageIO.createImageInputStream(is);
            reader.setInput(iis, true);
            ImageReadParam param=reader.getDefaultReadParam();
            Rectangle rect=new Rectangle(x, y, length, width);
            //提供一个BufferedImage,将其用作解码像素数据的目标
            param.setSourceRegion(rect);
            BufferedImage bi=reader.read(0, param);
            return bi;
        } finally {
            if (is != null) {
                is.close();
            }
            if (iis != null) {
                iis.close();
            }
        }

    }

    /**
     * 功能描述：第三步  --- 对原图的裁剪位置的上色标记处理
     *
     * @param targetLength-- 原图的长度, targetWidth-- 原图的宽度, x-- 裁剪区域的x坐标, y  裁剪区域的y坐标, length  抠图的长度, width抠图的宽度
     * @return int[][]
     ***/
    public int[][] getCutAreaData(int targetLength, int targetWidth, int x, int y, int length, int width) {
        int[][] data=new int[targetLength][targetWidth];
        for (int i=0; i < targetLength; i++) {
            for (int j=0; j < targetWidth; j++) {
                if (i < x + length && i >= x && j < y + width && j > y) {
                    data[i][j]=1;
                } else {
                    data[i][j]=0;
                }
            }
        }
        return data;
    }


    /**
     * 功能描述：第四步，0 是以外的地方，1是上色的地方，对1的地方上色处理
     *
     * @param oriImage, templateImage
     * @return java.awt.image.BufferedImage
     ***/
    public BufferedImage cutByTemplate(BufferedImage oriImage, int[][] templateImage) throws Exception {
        for (int i=0; i < oriImage.getWidth(); i++) {
            for (int j=0; j < oriImage.getHeight(); j++) {
                int rgb=templateImage[i][j];
                //原图中对应位置变色处理
                int rgb_ori=oriImage.getRGB(i, j);
                if (rgb == 1) {
                    int r=(0xff & rgb_ori);
                    int g=(0xff & (rgb_ori >> 8));
                    int b=(0xff & (rgb_ori >> 16));
                    int Gray=(r * 2 + g * 5 + b * 1) >> 3;
                    oriImage.setRGB(i, j, Gray);
                }
            }

        }
        return oriImage;
    }

    /**
     * 功能描述：第五步：高斯模糊图片
     * @param radius, horizontal
     * @return java.awt.image.ConvolveOp
     ***/
    public static ConvolveOp getGaussianBlurFilter(int radius, boolean horizontal) {
        if (radius < 1) {
            throw new IllegalArgumentException("Raduis must be >=1");
        }
        int size=radius*2 +1;
        float[] data=new float[size];
        float sigma=radius/3.0f;
        float twoSigmaSquare=2.0f * sigma* sigma;
        float sigmaRoot =(float)Math.sqrt(twoSigmaSquare*Math.PI);
        float total=0.0f;
        for (int i=-radius; i <= radius; i++) {
            float distance=i*i;
            int index=i+radius;
            data[index] =(float)Math.exp(-distance/twoSigmaSquare)/sigmaRoot;
            total+=data[index];
        }
        for (int i=0; i < data.length; i++) {
            data[i]/=total;
        }
        Kernel kernel=null;
        if (horizontal){
            kernel=new Kernel(size,1,data);
        }else {
            kernel=new Kernel(1,size,data);
        }
        return new ConvolveOp(kernel,ConvolveOp.EDGE_NO_OP,null);

    }

    public static BufferedImage simpleBlur(BufferedImage src,BufferedImage dest){
        BufferedImageOp op=getGaussianBlurFilter(2,false);
        return op.filter(src,dest);
    }

    /**
     * 功能描述： 第六步： 为图片添加边框（根据图片的额位置高宽等）
     * @param image, x, y, width, height
     * @return java.awt.image.BufferedImage
     ***/
    public BufferedImage imagesFrame(BufferedImage image,int x ,int y ,int width,int height) throws Exception{
        Graphics g=image.getGraphics();
        g.setColor(Color.RED);      //画笔颜色
        g.drawRect(x,y,width,height);       //矩形框(原点x坐标，原点y坐标，矩形的长，矩形的宽)
        return image;
    }

    public BufferedImage imagesFrameMin(BufferedImage image) throws Exception{
        int height=54;
        int width=54;
        Graphics g=image.getGraphics();
        Color c1=new Color(173,216,230);
        g.setColor(c1);
        g.drawRect(0,0,width,height);   //矩形框（原点x坐标，原点y坐标，矩形的长，矩形的宽）
        return image;
    }


    /**
     * 功能描述：第七步：  压缩的一个方法，选择性使用
     * @param bufferedImage
     * @return byte[]
     ***/
    public static byte[] compressPictures(BufferedImage bufferedImage) throws IOException{
        //得到指定Forma图片的writer
        Iterator<ImageWriter> iter=ImageIO.getImageWritersByFormatName("jpg");      //得到迭代器
        ImageWriter writer=(ImageWriter)iter.next();
        //获取指定Writer的输出参数设置（ImageWriteParam）
        ImageWriteParam imageWriteParam=writer.getDefaultWriteParam();
        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);      //设置可否压缩
        imageWriteParam.setCompressionQuality(1f);          //设置压缩质量参数
        imageWriteParam.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
        ColorModel colorModel=ColorModel.getRGBdefault();

        //指定压缩时使用的色彩模式
        imageWriteParam.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel,colorModel.createCompatibleSampleModel(16,16)));
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();        //取得内存输出流

        writer.setOutput(ImageIO.createImageOutputStream(byteArrayOutputStream));
        IIOImage iIamge=new IIOImage(bufferedImage,null,null);
        writer.write(null,iIamge,imageWriteParam);
        return byteArrayOutputStream.toByteArray();
    }


    public String imageToBase64(BufferedImage image) throws Exception{
        byte[] imagedata=null;
        ByteArrayOutputStream bao=new ByteArrayOutputStream();
        ImageIO.write(image,"jpg",bao);         //更换图片样式，png ,jpg
        imagedata=bao.toByteArray();
        BASE64Encoder encoder=new BASE64Encoder();
        String BASE64IMAGE=encoder.encodeBuffer(imagedata).trim();
        BASE64IMAGE=BASE64IMAGE.replaceAll("\n","").replaceAll("\r","");        //删除\r\n
        return BASE64IMAGE;
    }


}
