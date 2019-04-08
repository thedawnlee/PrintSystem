package com.wrq.enums;

import lombok.Getter;

/**
 * Created by wangqian on 2019/4/8.
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{

    NEW(0, "新订单"), /* 支付成功后便是新订单 */
    FINISHED(1, "完结"), /* 用户对店铺进行评价后完结 */
    CANCEL(2, "已取消"), /* 用户取消订单，退款 */
    NOY_PAY(3, "未支付"), /* 创建订单未支付时 */
    REFUSE_ORDER(4, "已拒绝"), /* 用户取消订单，退款 */
    PROCESSING_ORDER(5, "正在处理")
    ;

    /* 状态码 */
    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
