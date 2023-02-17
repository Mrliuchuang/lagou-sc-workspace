package org.example.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author liuchuang
 * @date 2023/1/18 18:27
 */
@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    /*@GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        Integer forObject = restTemplate.getForObject("http://localhost:8080/resume/openstate/"
                + userId, Integer.class);
        System.out.println("======>>>调⽤简历微服务，获取到⽤户" +
                userId + "的默认简历当前状态为：" + forObject);
        return forObject;
    }*/


    /*@GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        // 从EurekaServer获取指定微服务实例
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("lagou-service-resume");
        ServiceInstance serviceInstance = serviceInstanceList.get(0);
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        String url = "http://" + host + ":" + port + "/resume/openstate/" + userId;
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        System.out.println("======>>>调⽤简历微服务，获取到⽤户" +
                userId + "的默认简历当前状态为：" + forObject);
        return forObject;
    }*/

    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        String url = "http://lagou-service-resume/resume/openstate/" + userId;
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        System.out.println("======>>>调⽤简历微服务，获取到⽤户" +
                userId + "的默认简历当前状态为：" + forObject);
        return forObject;
    }

    @HystrixCommand(
            // 线程池标识，要保持唯⼀，不唯⼀的话就共⽤了
            threadPoolKey = "findResumeOpenStateTimeOutFallback",
            threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "1"), @HystrixProperty(name = "maxQueueSize", value = "20") // 等待队列⻓度
            },// 线程数
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            })
    @GetMapping("/checkStateTimeOut/{userId}")
    public Integer findResumeOpenStateTimeOut(@PathVariable Long userId) {
        String url = "http://lagou-service-resume/resume/openstate/" + userId;
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        System.out.println("======>>>调⽤简历微服务，获取到⽤户" +
                userId + "的默认简历当前状态为：" + forObject);
        return forObject;
    }

    @HystrixCommand(
            // 线程池标识，要保持唯⼀，不唯⼀的话就共⽤了
            threadPoolKey = "findResumeOpenStateTimeOutFallback",
            threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "2"), @HystrixProperty(name = "maxQueueSize", value = "20") // 等待队列⻓度
            },// 线程数
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
                    /**
                     * 8秒钟内，请求次数达到2个，并且失败率在50%以上，就跳闸
                     * 跳闸后活动窗⼝设置为3s
                     */
                    ,
                    //统计时间窗口定义
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "8000"),
                    //统计时间窗口内的最小请求数
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
                    //统计时间窗口内的错误数量百分比阈值
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    //自我修复时的活动窗口长度
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000")}, fallbackMethod = "myFallBack")
    @GetMapping("/checkStateTimeOutFallback/{userId}")
    public Integer findResumeOpenStateTimeOutFallback(@PathVariable Long userId) {
        String url = "http://lagou-service-resume/resume/openstate/" + userId;
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        System.out.println("======>>>调⽤简历微服务，获取到⽤户" +
                userId + "的默认简历当前状态为："  + forObject);
        return forObject;
    }

    /*
定义回退⽅法，返回预设默认值
注意：该⽅法形参和返回值与原始⽅法保持⼀致
*/
    public Integer myFallBack(Long userId) {
        return -123333; // 兜底数据
    }
}
