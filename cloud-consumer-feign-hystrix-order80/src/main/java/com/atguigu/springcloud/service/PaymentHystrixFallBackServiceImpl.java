package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author sevenxylee
 * @Date 2022/8/16 22:00
 **/
@Service
public class PaymentHystrixFallBackServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "paymentInfo_OK服务调用失败，提示来自：CLOUD-CONSUMER-HYSTRIX-ORDER";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "paymentInfo_Timeout服务调用失败，提示来自：CLOUD-CONSUMER-HYSTRIX-ORDER";
    }
}
