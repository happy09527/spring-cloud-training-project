package com.example.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author: ZhangX
 * @createDate: 2022/11/17
 * @description:
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamMain8802 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMain8802.class,args);
    }
}
