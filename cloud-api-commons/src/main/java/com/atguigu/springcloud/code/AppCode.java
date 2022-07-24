package com.atguigu.springcloud.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppCode implements StatusCode {
    APP_ERROR(2000, "业务异常"),
    PRICE_ERROR(2001, "价格异常"),
    RESPONSE_PACK_ERROR(1001,"响应包异常"),
    CURD_ERROR(3001, "数据库插入异常");

    private int code;
    private String msg;


}
