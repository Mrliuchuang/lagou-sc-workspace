package org.example.controller;


import org.example.service.ResumeServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author liuchuang
 * @date 2023/1/18 18:27
 */
@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {

    @Autowired
    private ResumeServiceFeignClient resumeServiceFeignClient;

    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        Integer defaultResumeState = resumeServiceFeignClient.findDefaultResumeState(userId);
        return defaultResumeState;
    }
}
