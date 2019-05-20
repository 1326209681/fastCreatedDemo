package cn.itcast.consumer.web;

import cn.itcast.consumer.pojo.User;
import com.netflix.discovery.DiscoveryClient;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * ConsumerController
 * hasee
 * 2019/1/27
 * 18:47
 *
 * @Version 1.0
 **/
@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private org.springframework.cloud.client.discovery.DiscoveryClient discoveryClient;

    @GetMapping("{id}")
    public User queryById(@PathVariable("id")Long id){
        //根据服务id获取
        List<ServiceInstance> instances=discoveryClient.getInstances("user-service");
        //从实例中取出ip和端口
        ServiceInstance instance=instances.get(0);
        String url="http://"+instance.getHost()+":"+instance.getPort()+"/user/"+id;
        User user=restTemplate.getForObject(url, User.class);
        return user;
    }
}
