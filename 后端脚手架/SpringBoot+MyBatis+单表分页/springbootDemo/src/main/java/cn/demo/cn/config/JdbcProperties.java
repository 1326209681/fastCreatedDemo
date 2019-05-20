package cn.demo.cn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: JdbcProperties
 * @author: 张磊
 * @create: 2019/5/15-21:48
 **/
@ConfigurationProperties(prefix = "jdbc")
@Data
public class JdbcProperties {
// 因为前缀是jdbc，所以在注解里要配置前缀jdbc
    String url;
    String driverClassName;
    String username;
    String password;
}
