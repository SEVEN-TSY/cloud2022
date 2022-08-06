package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author sevenxylee
 * @Date 2022/7/25 22:50
 **/
@RestController
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        log.info("Feign客户端调用/consumer/payment/get/{id}");
        return paymentFeignService.getPaymentById(id);
    }
    @GetMapping("/consumer/payment/autopack/get/{id}")
    public CommonResult getPaymentAutoPack(@PathVariable("id") Long id){
        log.info("Feign客户端调用/consumer/payment/get/{id}");
        return paymentFeignService.getPaymentByIdAutoPack(id);
    }
}
