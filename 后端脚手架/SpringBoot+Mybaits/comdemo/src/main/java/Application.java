import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @program: Application
 * @author: 张磊
 * @create: 2019/4/1-23:07
 **/
@SpringBootApplication
@ComponentScan(basePackages = "com.demo")
@MapperScan("com.demo.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
