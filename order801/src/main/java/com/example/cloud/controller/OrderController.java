package com.example.cloud.controller;

import com.example.cloud.entities.CommonResult;
import com.example.cloud.entities.Payment;
import com.example.cloud.lb.MyBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    //        private static final String paymentUrl = "http://localhost:8001";
    public static final String paymentUrl = "http://CLOUD-PAYMENT-SERVICE"; // 使用的是EurekaServer中注册中心的服务名称

    // 自己实现的负载均衡规则
    @Resource
    private MyBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping("consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(paymentUrl + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("consumer/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(paymentUrl + "/payment/get/" + id, CommonResult.class);
    }
    @GetMapping("consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(paymentUrl + "/payment/" + id, CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }
        return new CommonResult<>(400,"操作失败");
    }

    @GetMapping("consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance instance = loadBalancer.instance(instances); // 使用自己的负载均衡规则获取服务器实例
        return restTemplate.getForObject(instance.getUri() + "/payment/lb/loadBalancer", String.class);
    }

    // zipkin 测试
    @GetMapping("consumer/payment/zipkin")
    public String paymentZipkin() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance instance = loadBalancer.instance(instances); // 使用自己的负载均衡规则获取服务器实例
        return restTemplate.getForObject(instance.getUri() + "/payment/zipkin", String.class);
    }
}
