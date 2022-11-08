package cn.tfyhyc.springcloud.service;

import cn.tfyhyc.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @author 头发又黑又长
 * @Date 2022/10/2 23:37
 * @email zwb15083976291@163.com
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentId(@Param("id") Long id);

}
