package com.example.cloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

/**
 * @author: ZhangX
 * @createDate: 2022/11/5
 * @description:
 */
@SpringBootApplication
@EnableHystrix // 启用断路器
public class PaymentHystrix8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrix8001.class,args);
    }
    /**
     *
     * 此配置是为了服务监控而配置，与服务容错本身无关，springcLoud升级后的坑
     * ServletRegistrationBean因为springboot的默认路径不是"/hystrix.stream" ,
     * 只要在自己的项目里配置上下面的servlet就可以了
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");

        return registrationBean;
    }
}