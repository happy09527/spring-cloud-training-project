package com.example.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient // 表示自己是Eureka 的客户端
@EnableDiscoveryClient // 自动配置服务发现 对于注册进Eureka里面的微服务，可以通过服务发现来获得服务的信息,在h版开始可以省略这两个接口eurekaclient
public class PaymentMain801 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain801.class, args);
    }
}
