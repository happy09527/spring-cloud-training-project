package cn.tfyhyc.springcloud.controller;

import cn.tfyhyc.springcloud.entities.CommonResult;
import cn.tfyhyc.springcloud.entities.Payment;
import cn.tfyhyc.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zx
 * @Date 2022/10/2 23:40
 */
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentId(id);
        return !ObjectUtils.isEmpty(result) ? new CommonResult<>(200, "查询成功", result) :
                new CommonResult<>(500, "查询失败，记录不存在", result);
    }

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("******插入结果：{}", result);
        return result > 0 ? new CommonResult(200, "插入数据成功", result) :
                new CommonResult(500, "插入数据失败", null);
    }

    // 测试手写负载均衡
    @GetMapping("/lb/loadBalancer")
    public String getPaymentLb() {
        return serverPort;
    }
}