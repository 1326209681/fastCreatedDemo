package cn.itcast.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * JdbcProperties
 * hasee
 * 2019/1/26
 * 12:46
 *
 * @Version 1.0
 **/
@ConfigurationProperties(prefix = "jdbc")  //这里使用jdbc加前缀是为了跟配置文件的jdbc开头对应。加上jdbc是为了易于维护，以后也可能要配置其他的类似名字以便于区分；
@Data
public class JdbcProperties {
    String url;
    String driverClassName;
    String username;
    String password;
}
