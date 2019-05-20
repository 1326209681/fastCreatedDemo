package cn.itcast.demo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import java.io.Writer;
import java.util.*;

/**
 * Test
 * hasee
 * 2018/12/4
 * 15:17
 *
 * @Version 1.0
 **/

public class Test {
    public static void main(String[] args) throws IOException, TemplateException {
        //创建一个配置对象,获取该对象的版本
        Configuration configuration=new Configuration(Configuration.getVersion());
        //获取模板所在目录
        configuration.setDirectoryForTemplateLoading(new File("D:\\freemarkerDemo\\src\\main\\resources"));
        //设置编码集
        configuration.setDefaultEncoding("utf-8");
        //获取模板对象
        Template template=configuration.getTemplate("test.ftl");
        //给值
        Map map=new HashMap();
        map.put("name","张三丰");
        map.put("message","欢迎来到神奇的Freemarker世界！");
        //if指令默认给值
        map.put("success",false);

        //list集合给值
        List goodsList=new ArrayList();
        Map goods1=new HashMap();
        goods1.put("name", "苹果");
        goods1.put("price", 5.8);
        Map goods2=new HashMap();
        goods2.put("name", "香蕉");
        goods2.put("price", 2.5);
        Map goods3=new HashMap();
        goods3.put("name", "橘子");
        goods3.put("price", 3.2);
        goodsList.add(goods1);
        goodsList.add(goods2);
        goodsList.add(goods3);
        map.put("goodsList", goodsList);

        //给日期格式化赋值
        map.put("today",new Date());
        //数字转为字符串给值
        map.put("point",135465);



        //输出对象
        Writer out=new FileWriter("D:\\freemarkerDemo\\src\\main\\java\\cn\\itcast\\demo\\test.html");
        //输出
        template.process(map,out);
        //关闭
        out.close();
    }
}
