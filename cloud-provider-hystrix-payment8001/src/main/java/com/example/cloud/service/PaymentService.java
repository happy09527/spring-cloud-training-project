package com.example.cloud.service;

/**
 * @author: ZhangX
 * @createDate: 2022/11/5
 * @description:
 */


public interface PaymentService {
    public String paymentInfo_OK(Integer id);

    public String paymentInfo_TimeOut(Integer id);

    String paymentCircuitBreaker(Integer id);
}
