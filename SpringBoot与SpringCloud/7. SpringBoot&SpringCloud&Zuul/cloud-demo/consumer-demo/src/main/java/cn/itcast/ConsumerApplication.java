package cn.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
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
/*@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication*/
@EnableFeignClients
@SpringCloudApplication
public class ConsumerApplication {
//
//    @Bean
//    @LoadBalanced       //此注解拦截器，拦截请求，然后负载均衡算法url:  分配一个地址给url调用；
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }

    public static void main(String[] args){
        SpringApplication.run(ConsumerApplication.class,args);
    }
}


























