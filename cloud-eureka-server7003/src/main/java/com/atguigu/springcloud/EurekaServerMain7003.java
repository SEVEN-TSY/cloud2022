package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description TODO
 * @Author sevenxylee
 * @Date 2022/7/13 22:50
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerMain7003 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerMain7003.class,args);
    }
}
