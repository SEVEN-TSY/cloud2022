package com.atguigu.springcloud.advice;

import com.atguigu.springcloud.code.ResultCode;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.exception.APIException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description 控制层统一异常处理
 * @Author sevenxylee
 * @Date 2022/7/24 17:19
 **/
@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler({BindException.class})
    public CommonResult MethodArgumentNotValidExceptionHandler(BindException e){
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new CommonResult(ResultCode.VALIDATE_ERROR,objectError.getDefaultMessage());
    }

    @ExceptionHandler({APIException.class})
    public CommonResult APIExceptionHandler(APIException e){
        return new CommonResult(e.getCode(),e.getMsg(),e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public CommonResult ExceptionHandler(Exception e){

        System.out.println("Exception.class");
        return new CommonResult(ResultCode.FAILED,e.getLocalizedMessage());
    }

}
