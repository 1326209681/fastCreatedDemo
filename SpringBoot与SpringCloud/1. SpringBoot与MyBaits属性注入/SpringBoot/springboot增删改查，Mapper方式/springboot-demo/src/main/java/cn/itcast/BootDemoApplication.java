package cn.itcast;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * BootDemoApplication
 * hasee
 * 2019/1/26
 * 11:35
 *
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan("cn.itcast.mapper")
public class BootDemoApplication  {
    public static void main(String[] args) {
        SpringApplication.run(BootDemoApplication.class,args);
    }
}
