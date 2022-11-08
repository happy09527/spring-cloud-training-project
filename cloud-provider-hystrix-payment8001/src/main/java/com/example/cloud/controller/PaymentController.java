package com.example.cloud.controller;

import com.example.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id) {
        String res = paymentService.paymentInfo_OK(id);
        log.info(res);
        return res;
    }
    /**
     * @author: ZhangX
     * @date: 2022/11/6 16:52
     * @param: [id]
     * @return: java.lang.String
     * @description: 服务降级
     **/
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") Integer id) {
        String res = paymentService.paymentInfo_TimeOut(id);
        log.info(res);
        return res;
    }
    /**
     * @author: ZhangX
     * @date: 2022/11/6 16:52
     * @param: [id]
     * @return: java.lang.String
     * @description: 服务熔断
     **/
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        return paymentService.paymentCircuitBreaker(id);
    }
}
