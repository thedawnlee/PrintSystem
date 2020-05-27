package com.dawn.enums;

import lombok.Getter;


@Getter
public enum CommentTypeEnum {

    SHARE(0, "分享"),
    SHOP(1, "店铺"),
    ;

    /* 状态码 */
    private Integer code;

    private String message;

    CommentTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
