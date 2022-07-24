package com.atguigu.springcloud.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
//不需要进行统一包装成CommonResult返回值的接口添加此注解
public @interface NoControllerResponseAdvice {

}
