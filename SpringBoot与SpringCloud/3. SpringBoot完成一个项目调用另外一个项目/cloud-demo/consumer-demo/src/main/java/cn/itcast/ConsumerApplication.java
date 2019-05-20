package cn.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * ConsumerApplication
 * hasee
 * 2019/1/27
 * 18:42
 *
 * @Version 1.0
 **/
@SpringBootApplication
public class ConsumerApplication {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args){
        SpringApplication.run(ConsumerApplication.class,args);
    }
}
