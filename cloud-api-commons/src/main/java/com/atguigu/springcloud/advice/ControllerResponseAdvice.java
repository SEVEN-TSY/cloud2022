package com.atguigu.springcloud.advice;

import com.atguigu.springcloud.annotation.NoControllerResponseAdvice;
import com.atguigu.springcloud.code.AppCode;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.exception.APIException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Description TODO
 * @Author sevenxylee
 * @Date 2022/7/24 22:09
 **/
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return !(methodParameter.hasMethodAnnotation(NoControllerResponseAdvice.class)||methodParameter.getParameterType().isAssignableFrom(CommonResult.class));
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //String类型不能直接封装
        if(methodParameter.getGenericParameterType().equals(String.class)){
            try {
                return new ObjectMapper().writeValueAsString(new CommonResult<>(o));
            } catch (JsonProcessingException e) {
                throw new APIException(AppCode.RESPONSE_PACK_ERROR,"String返回类型统一返回转换异常");
            }
        }
        return new CommonResult<>(o);
    }
}
