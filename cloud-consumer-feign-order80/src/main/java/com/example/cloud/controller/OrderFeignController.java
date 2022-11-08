package com.example.cloud.controller;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import com.example.cloud.entities.CommonResult;
import com.example.cloud.entities.Payment;
import com.example.cloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: ZhangX
 * @createDate: 2022/11/5
 * @description:
 */
@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id){
        return paymentFeignService.getPaymentById(id);
    }
    /**
     * @author: ZhangX
     * @date: 2022/11/5 16:56
     * @param: []
     * @return: com.example.cloud.entities.CommonResult<java.lang.String>
     * @description:  openfeign-ribbon 客户端一般默认等待1秒钟
     * 如果超时将引发 java.net.SocketTimeoutException: Read timed out
     **/
    @GetMapping("consumer/payment/feign/timeout")
    public CommonResult<String> paymentFeignTimeout(){
        return paymentFeignService.paymentFeignTimeout();
    }
}
