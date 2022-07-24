package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Description 订单微服务调用者
 * @Author sevenxylee
 * @Date 2022/7/10 22:40
 **/
@RestController
@Slf4j
public class OrderController {
    private static final String PaymentServer_URL="http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment){
        log.info("客户端调用/consumer/payment/create");
        return restTemplate.postForObject(PaymentServer_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        log.info("客户端调用/consumer/payment/get/{id}");
        return restTemplate.getForObject(PaymentServer_URL+"/payment/get/"+id,CommonResult.class,id);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult getEntityPayment(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> responseEntity = restTemplate.getForEntity(PaymentServer_URL + "/payment/get/" + id, CommonResult.class, id);
        log.info("客户端调用/consumer/payment/getForEntity/{id}");
        if (responseEntity.getStatusCode().is2xxSuccessful()){
            log.info(""+responseEntity.getHeaders());
            return responseEntity.getBody();
        }else {
            log.info(responseEntity.getStatusCode()+"\t"+responseEntity.getHeaders());
            return new CommonResult(444,"系统异常！");
        }
    }

    @GetMapping("/consumer/payment/postForEntity/create")
    public CommonResult create2(Payment payment){
        ResponseEntity<CommonResult> responseEntity = restTemplate.postForEntity(PaymentServer_URL + "/payment/create", payment, CommonResult.class);
        log.info("客户端调用/consumer/payment/postForEntity/create");
        if (responseEntity.getStatusCode().is2xxSuccessful()){
            log.info(""+responseEntity.getHeaders());
            return responseEntity.getBody();
        }else {
            log.info(responseEntity.getStatusCode()+"\t"+responseEntity.getHeaders());
            return new CommonResult(444,"系统异常！");
        }
    }


}
