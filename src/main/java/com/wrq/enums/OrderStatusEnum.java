package com.wrq.enums;

import lombok.Getter;

/**
 * Created by wangqian on 2019/4/8.
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{

    /* 40 50 60 70 都是支付完毕后的状态 */
    CANCELED(0,"已取消"),
    NO_PAY(10,"未支付"),
    PAID(20,"已付款"),
    PROCESSING_ORDER(40,"正在处理"),
    ORDER_REFUSED(50,"订单被拒绝"),
    ORDER_SUCCESS(60,"订单关闭"),
    ORDER_CLOSE(70 , "订单关闭");

    /* 状态码 */
    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
