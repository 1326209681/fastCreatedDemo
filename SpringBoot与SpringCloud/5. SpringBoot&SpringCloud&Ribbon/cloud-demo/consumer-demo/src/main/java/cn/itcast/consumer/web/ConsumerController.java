package cn.itcast.consumer.web;

import cn.itcast.consumer.pojo.User;
import com.netflix.discovery.DiscoveryClient;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
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

/*    @Autowired
    private RibbonLoadBalancerClient client;*/

  /*  @Autowired
    private org.springframework.cloud.client.discovery.DiscoveryClient discoveryClient;*/

    @GetMapping("{id}")
    public User queryById(@PathVariable("id")Long id){

        //原始方式：根据服务id获取
       // List<ServiceInstance> instances=discoveryClient.getInstances("user-service");
        //从实例中取出ip和端口
   //     ServiceInstance instance=instances.get(0);


        //使用Ribbon的负载均衡算法，它自动帮我们取出一个实例
        //算法有随机，轮询，hash等方式，默认采用随机
      //  ServiceInstance instance=client.choose("user-service");

      //  String url="http://"+instance.getHost()+":"+instance.getPort()+"/user/"+id;

        /*
        * 这里的url地址通过前面的拦截器拦截请求，然后将user-service的地址使用负载均衡算法匹配一个
        * 然后赋值进url
        * 其作用效果跟上面的额client.choose一样，它是隐藏了这些细节，自动完成而已；
        * */

        String url="http://user-service/user/"+id;

        User user=restTemplate.getForObject(url, User.class);
        return user;
    }
}






















