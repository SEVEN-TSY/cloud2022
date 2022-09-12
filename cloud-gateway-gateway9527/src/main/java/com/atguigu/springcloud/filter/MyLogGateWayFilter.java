package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author lixinyi03
 * @version 1.0
 * @date 2022/8/30 16:45
 * @Description 微服务网关日志记录
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ApplicationContext applicationContext = exchange.getApplicationContext();
        Map<String, Object> attributes = exchange.getAttributes();
        log.info("request.getPath()\t:"+request.getPath());
        log.info("request.getQueryParams()\t:"+request.getQueryParams());
        log.info("request.getHeaders()\t:"+request.getHeaders().toString());
        log.info("request.getBody()\t:"+request.getBody());
        assert applicationContext != null;
        log.info("request.getBody()\t:"+applicationContext.getApplicationName());
        log.info("request.getBody()\t:"+applicationContext.getDisplayName());
        log.info("request.getBody()\t:"+applicationContext.getAutowireCapableBeanFactory());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
