package com.example.cloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.example.cloud.service.PaymentService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author: ZhangX
 * @createDate: 2022/11/5
 * @description:
 */

@Service
public class PaymentServiceImpl implements PaymentService {
    /**
     * 正常访问的服务
     *
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池" + Thread.currentThread().getName() + " paymentInfo_OK, id" + id + "\t" + "O(∩_∩)O haha~~";
    }

    /**
     * 超时的服务
     *
     * @param id
     * @return hystrixCommand 服务降级处理
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "5000")
            })
    public String paymentInfo_TimeOut(Integer id) {
        int timeNumber = 3;
        // 暂停几秒线程
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池" + Thread.currentThread().getName() + " paymentInfo_TimeOut, id" + id + "\t" + "O(∩_∩)O haha~~" + "  耗时" + timeNumber + "秒";
    }

    /**
     * @author: ZhangX
     * @date: 2022/11/6 16:53
     * @param: [id]
     * @return: java.lang.String
     * @description: 服务熔断
     **/
    @Override
    @HystrixCommand(fallbackMethod = "cicuitBreakerFallBack", commandProperties = {
            // 是否开启短路器
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED, value = "true"),
            // 请求次数
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "10"),
            // 熔断时间窗口期
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "10000"),
            // 失败率达到多少后跳闸
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "60")
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("************** id 不能为复数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t 调用成功,流水号：" + serialNumber;    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池" + Thread.currentThread().getName() + "超时";
    }

    public String cicuitBreakerFallBack(@PathVariable("id") Integer id){
        return "服务熔断";
    }
}
