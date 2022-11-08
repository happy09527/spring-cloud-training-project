package com.example.cloud.service;

import com.example.cloud.entities.CommonResult;
import com.example.cloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: ZhangX
 * @createDate: 2022/11/5
 * @description: 完成OpenFeign接口+注解实现ribbon+restTemplate的负载均衡
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("payment/feign/timeout")
    public CommonResult<String> paymentFeignTimeout();
}
