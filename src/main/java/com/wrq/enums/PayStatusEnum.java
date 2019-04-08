package com.wrq.enums;

import lombok.Getter;

/**
 * Created by wangqian on 2019/4/8.
 */
@Getter
public enum PayStatusEnum implements CodeEnum {

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    REFUND(2, "退款成功")
    ;

    /* 状态码 */
    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
