package cn.itcast.consumer.client;

import cn.itcast.consumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * UserClient
 * hasee
 * 2019/1/29
 * 14:54
 *
 * @Version 1.0
 **/
@FeignClient(value = "user-service",fallback = UserClientFallback.class)        //交代服务名称
public interface UserClient {
    @GetMapping("user/{id}")        //指明请求方式和地址以及参数
    User queryById(@PathVariable("id")Long id);     //自动注入id值
}
