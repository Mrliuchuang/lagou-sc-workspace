package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author liuchuang
 * @date 2023/1/18 18:28
 */

/**
 * 注解简化写法
 *
 * @SpringCloudApplication =
 * @SpringBootApplication+@EnableDiscoveryClient+@EnableCircuitBrea ker
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AutodeliverApplication8096 {
    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplication8096.class, args);
    }
}
