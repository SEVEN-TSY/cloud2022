package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description TODO
 * @Author sevenxylee
 * @Date 2022/7/21 22:51
 **/
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRandomRule(){
        return new RandomRule();
    }
}
