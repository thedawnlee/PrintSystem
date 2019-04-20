package com.wrq.enums;

import lombok.Getter;

/**
 * Created by wangqian on 2019/4/19.
 */
@Getter
public enum TagNameEnum implements CodeEnum{


    TAG_OTHERS(0,"已取消"), /* 未支付，用户点击取消订单 */
    TAG_POSTGRADUATE(1,"未支付"),
    TAG_EXAMINATION(2,"已付款"),
    TAG_PAPER(3,"待取货"),
    TAG_CIVIL_SERVANT(4,"店主拒绝"),
    TAG_CET(5,"订单完结");

    /* 状态码 */
    private Integer code;

    private String message;

    TagNameEnum (Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
