package com.atguigu.springcloud.entities;

import com.atguigu.springcloud.code.ResultCode;
import com.atguigu.springcloud.code.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author sevenxylee
 * @Date 2022/7/6 22:30
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private Object data;

    //手动设置返回的状态码和信息
    //AllArgsConstructor

    //手工设置空对象返回的状态码和信息
    public CommonResult(Integer code,String message){
        this(code,message,null);
    }

    //空对象设置返回的状态码
    public CommonResult(StatusCode statusCode){
        this.code=statusCode.getCode();
        this.message=statusCode.getMsg();
        this.data=null;
    }

    //默认设置返回成功的状态码对象
    public CommonResult(Object data){
        this.code= ResultCode.SUCCESS.getCode();
        this.message=ResultCode.SUCCESS.getMsg();
        this.data=data;
    }

    //非空对象自主选择返回的状态码对象
    public CommonResult(StatusCode statusCode,Object data){
        this.code=statusCode.getCode();
        this.message=statusCode.getMsg();
        this.data=data;
    }

}
