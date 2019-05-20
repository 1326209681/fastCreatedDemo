package cn.demo.cn.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @program: JdbcConfig
 * @author: 张磊
 * @create: 2019/5/15-21:51
 **/
@Configuration
@EnableConfigurationProperties(JdbcProperties.class)
public class JdbcConfig {

    /*两种构造方式*/
//    @Autowired
//    JdbcProperties jdbcProperties;
//
//    public JdbcConfig(JdbcProperties jdbcProperties){
//        this.jdbcProperties=jdbcProperties;
//    }
//    @Bean
//    public DataSource dataSource(JdbcProperties prop){
//        DruidDataSource dataSource
//    }

//    @Bean
//    public DataSource dataSource(JdbcProperties prop){
//        DruidDataSource dataSource=new DruidDataSource();
//        dataSource.setDriverClassName(prop.getDriverClassName());
//        dataSource.setUrl(prop.getUrl());
//        dataSource.setUsername(prop.getUsername());
//        dataSource.setPassword(prop.getPassword());
//        return dataSource;
//    }
    //上面的注入Bean的Datasource是传统的，还有更优雅的写法。看下方,效果一样
    @Bean
    @ConfigurationProperties(prefix = "jdbc")
    public DataSource dataSource(){
        return new DruidDataSource();
    }
}
