package cn.tfyhyc.springcloud.dao;

import cn.tfyhyc.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 头发又黑又长
 * @Date 2022/10/2 23:22
 */
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentId(@Param("id") Long id);


}
