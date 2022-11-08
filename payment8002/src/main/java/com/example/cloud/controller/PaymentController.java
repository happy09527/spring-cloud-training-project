package com.example.cloud.controller;

import com.example.cloud.entities.CommonResult;
import com.example.cloud.entities.Payment;
import com.example.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient; // 服务发现客户端

    @GetMapping("/payment/discovery")
    public Object getDiscovery() {
        // 获取EurekaServer上的所有服务
        List<String> services = discoveryClient.getServices();
        for(String service : services){
            log.info(service);
        }
        // 通过服务名称获取实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("服务名称:{}  ip地址:{}  端口号:{}  url地址：{}", instance.getServiceId(),
                    instance.getHost(), instance.getPort(), instance.getUri());
        }
        return discoveryClient;
    }

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("**************插入结果" + result + "asd");
        if (result > 0) {
            return new CommonResult(200, "插入成功" + serverPort, result);
        } else {
            return new CommonResult(400, "插入失败" + serverPort);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("**************查询结果" + payment);
        if (payment != null) {
            return new CommonResult(200, "查找成功" + serverPort, payment);
        } else {
            return new CommonResult(400, "查找失败" + serverPort);
        }
    }

    // 测试手写负载均衡
    @GetMapping("/payment/lb/loadBalancer")
    public String getPaymentLb() {
        return serverPort;
    }
}
