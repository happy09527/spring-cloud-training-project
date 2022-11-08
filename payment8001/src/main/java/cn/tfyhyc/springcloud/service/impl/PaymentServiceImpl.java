package cn.tfyhyc.springcloud.service.impl;

import cn.tfyhyc.springcloud.dao.PaymentDao;
import cn.tfyhyc.springcloud.entities.Payment;
import cn.tfyhyc.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 头发又黑又长
 * @Date 2022/10/2 23:37
 * @email zwb15083976291@163.com
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    PaymentDao paymentDao;


    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentId(Long id) {
        return paymentDao.getPaymentId(id);

    }
}
