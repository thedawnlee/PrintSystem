package com.wrq.enums;

/**
 * Created by wangqian on 2019/4/1.
 */
public enum PageSizePriceEnum {

    SIZE_A0(0, "A0尺寸"),
    SIZE_A1(1, "A1尺寸"),
    SIZE_A2(2, "A2尺寸"),
    SIZE_A3(3, "A3尺寸"),
    SIZE_A4(4, "A4尺寸"),
    SIZE_A5(5, "A5尺寸"),
    SIZE_A6(6, "A6尺寸"),
    SIZE_A7(7, "A7尺寸"),
    SIZE_A8(8, "A8尺寸"),
    SIZE_A9(9, "A9尺寸"),
    SIZE_A10(10, "A10尺寸"),
    SIZE_4A0(11, "4A0尺寸"),
    SIZE_0A0(12, "0A0尺寸"),
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

    PageSizePriceEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
