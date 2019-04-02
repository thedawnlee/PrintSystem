package com.wrq.enums;

/**
 * Created by wangqian on 2019/3/30.
 */
public enum ColorTypeEnum {

    BLACK(0, "黑白"),
    COLORFUL(1, "彩色"),
    ;

    /* 状态码 */
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private String message;

    ColorTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
