package com.example.cloud.controller;

import com.example.cloud.entities.CommonResult;
import com.example.cloud.entities.Payment;
import com.example.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("**************插入结果" + result + "asd");
        if (result > 0) {
            return new CommonResult(200, "插入成功", result);
        } else {
            return new CommonResult(400, "插入失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("**************查询结果" + payment + serverPort);
        if (payment != null) {
            return new CommonResult(200, "查找成功", payment);
        } else {
            return new CommonResult(400, "查找失败");
        }
    }

    // 测试手写负载均衡
    @GetMapping("/payment/lb/loadBalancer")
    public String getPaymentLb() {
        return serverPort;
    }

    /**
     * @author: ZhangX
     * @date: 2022/11/5 16:52
     * @param: []
     * @return: java.lang.String
     * @description: 测试feign的超时功能设置
     **/
    @GetMapping("payment/feign/timeout")
    public CommonResult getServerPort() {
        // 暂停几秒线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new CommonResult(200,"成功",serverPort);
    }
}
