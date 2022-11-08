package com.example.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // 自动配置 Eureka Server 配置
public class EurekaMain702 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain702.class, args);
    }
}