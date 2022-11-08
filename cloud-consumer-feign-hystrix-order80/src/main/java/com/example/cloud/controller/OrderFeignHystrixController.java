package com.example.cloud.controller;

import com.example.cloud.service.OrderFeignHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.parallel.Execution;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: ZhangX
 * @createDate: 2022/11/5
 * @description: 接口+注解实现客户端负载均衡
 */
@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "globalHandler") // 全局异常处理方法
public class OrderFeignHystrixController {
    @Resource
    private OrderFeignHystrixService orderFeignHystrixService;

    /**
     * @author: ZhangX
     * @date: 2022/11/5 20:53
     * @param: [id]
     * @return: java.lang.String
     * @description:
     **/
    @GetMapping("consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        log.info(Thread.currentThread().getName() + "timeout");
        return orderFeignHystrixService.paymentInfo_OK(id);
    }

    /**
     * @author: ZhangX
     * @date: 2022/11/6 16:05
     * @param: [id]
     * @return: java.lang.String
     * @description: 局部hystrix降级处理
     **/
//    @GetMapping("consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",
//            commandProperties = {
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
//                            value = "3000")
//            })
//    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
//        log.info(Thread.currentThread().getName() + "耗时<5s");
//        return orderFeignHystrixService.paymentInfo_Timeout(id);
//    }
//
//    public String paymentInfo_TimeOutHandler(@PathVariable("id") Integer id) {
//        log.info(Thread.currentThread().getName() + "timeout");
//        return Thread.currentThread().getName() + "timeout OR Error";
//    }
    /**
     * @author: ZhangX
     * @date: 2022/11/6 16:07
     * @param: [id]
     * @return: java.lang.String
     * @description: 全局hystrix异常降级
     **/
    @GetMapping("consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        log.info(Thread.currentThread().getName() + "耗时<5s");
        return orderFeignHystrixService.paymentInfo_Timeout(id);
    }

//    public String globalHandler(){
//        return "全局hystrix降级处理";
//    }
}

