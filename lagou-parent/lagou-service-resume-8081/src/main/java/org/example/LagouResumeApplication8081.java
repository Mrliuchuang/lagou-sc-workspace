package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liuchuang
 * @date 2023/1/18 17:44
 */
@SpringBootApplication
@EntityScan("org.example.dao.pojo")
//@EnableEurekaClient
@EnableDiscoveryClient
public class LagouResumeApplication8081 {
    public static void main(String[] args) {
        SpringApplication.run(LagouResumeApplication8081.class, args);
    }
}
