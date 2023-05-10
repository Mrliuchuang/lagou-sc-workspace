package org.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liuchuang
 * @date 2023/3/10 15:31
 */
// name：调⽤的服务名称，和服务提供者yml⽂件中spring.application.name保持⼀致
@FeignClient(name="lagou-service-resume")
@RequestMapping("/resume")
public interface ResumeServiceFeignClient {

    @GetMapping("/openstate/{userId}")
    public Integer findDefaultResumeState(@PathVariable("userId") Long userId);

}
