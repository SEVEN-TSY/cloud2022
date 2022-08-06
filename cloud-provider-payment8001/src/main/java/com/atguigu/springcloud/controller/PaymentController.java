package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.annotation.NoControllerResponseAdvice;
import com.atguigu.springcloud.code.AppCode;
import com.atguigu.springcloud.code.ResultCode;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.exception.APIException;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 订单微服务提供者
 * @Author sevenxylee
 * @Date 2022/7/6 23:28
 **/
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("serverPort:"+serverPort+"；插入结果，主键ID："+payment.getId());
        if(result>0){
            return new CommonResult(200,"插入数据库成功!serverPort：" +serverPort,payment.getId());
        }else {
            //return new CommonResult(444,"插入数据库失败",null);
            throw new APIException(AppCode.CURD_ERROR,payment.toString()+"写入异常");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("serverPort:"+serverPort+"；查询结果："+payment);
        if (payment!=null){
            //return new CommonResult(payment);
            //int i= (int) (payment.getId()/0);
            return new CommonResult(200,"查询成功!serverPort：" +serverPort,payment.getId());
        }else {
            //return new CommonResult(ResultCode.FAILED);

            throw new APIException(AppCode.APP_ERROR,id+"订单号不存在");
        }

    }
    @GetMapping("/payment/discovery")
    @NoControllerResponseAdvice
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service: "+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("instance serviceId:"+instance.getServiceId()+"\t" +
                    "instance hostname:"+instance.getHost()+"\t" +
                    "instance port:"+instance.getPort()+"\t" +
                    "instance Uri:"+instance.getUri()+"\t" +
                    "instance scheme:"+instance.getScheme()+"\t" +
                    "instance metadata:"+instance.getMetadata());
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/time")
    public String getTime(){
        System.out.println("hello world github lxeey");
        System.out.println("hello world github lxeey");
        return String.valueOf(System.currentTimeMillis());
    }

}
