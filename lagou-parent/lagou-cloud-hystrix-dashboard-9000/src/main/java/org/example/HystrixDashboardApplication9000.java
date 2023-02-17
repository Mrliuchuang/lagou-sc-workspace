package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author liuchuang
 * @date 2023/2/10 16:49
 */
@SpringBootApplication
@EnableHystrixDashboard // 开启hystrix dashboard
public class HystrixDashboardApplication9000 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication9000.class, args);
    }
}
