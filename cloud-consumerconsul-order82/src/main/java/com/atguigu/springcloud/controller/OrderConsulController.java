package com.atguigu.springcloud.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description TODO
 * @Author sevenxylee
 * @Date 2022/7/21 23:54
 **/
@RestController
public class OrderConsulController {

    public static final String  SERVICE_URL="http://consul-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul/info")
    public String info(){
        String result = restTemplate.getForObject(SERVICE_URL + "/payment/consul/info", String.class);
        System.out.println("消费者调用支付服务(consul)--->result:" + result);
        return result;
    }
}
