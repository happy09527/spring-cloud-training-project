package com.example.cloud.service.impl;

import com.example.cloud.service.OrderFeignHystrixService;
import org.springframework.stereotype.Service;

/**
 * @author: ZhangX
 * @createDate: 2022/11/6
 * @description:
 */

@Service
public class OrderFeignHystrixServiceImpl implements OrderFeignHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "fall back paymentInfo_OK";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "fall back paymentInfo_Timeout";
    }
}
