package com.atguigu.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.exception.APIException;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author sevenxylee
 * @Date 2022/8/6 11:08
 **/
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentInfo_GlobalHandler")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/get/{id}")
    @HystrixCommand
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_OK(id);
        //int i=1/0;
        log.info("serverPort:"+serverPort+"\t接口URI：/payment/hystrix/ok/get/{"+id+"}调用成功");
        return result;
    }
    @GetMapping("/payment/hystrix/timeout/get/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")})
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        //int i= 1/0;
        String result = paymentService.paymentInfo_Timeout(id);
        log.info("serverPort:"+serverPort+"\t接口URI：/payment/hystrix/timeout/get/{"+id+"}调用成功");
        return result;
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "/(ㄒoㄒ)/调用支付接口/payment/hystrix/timeout/get/{id}超时或异常：\t"+ "\t当前线程池名字" + Thread.currentThread().getName();
    }
    public String paymentInfo_GlobalHandler(){
        return "/(ㄒoㄒ)/全局fallback：\t"+ "\t当前线程池名字" + Thread.currentThread().getName();
    }

    @GetMapping("/payment/circuit/{id}")
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "12000"),//窗口期时长
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "15"),//窗口期开启计算的最小阈值
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "3000"),//半开休眠时间
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//设置断路器开启的错误百分比
            //以上配置规则为12秒之内有超过15个请求，失败率超过60%则开启断路器，3秒后半开
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String serialNumber = IdUtil.simpleUUID();
        if(id < 0)
        {
            return Thread.currentThread().getName()+"\t"+"负数，调用失败，" ;
        }

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }



}
