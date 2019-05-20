import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: Application
 * @author: 张磊
 * @create: 2019/4/1-23:07
 **/
@SpringBootApplication
@ComponentScan(basePackages = { "controller" })
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
