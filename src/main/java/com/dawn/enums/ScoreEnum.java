package com.dawn.enums;

import lombok.Getter;


@Getter
public enum ScoreEnum {

    NOT_LOOK(0, "未查看消息"),
    LOOK_OVER(1, "已查看消息"),
    ;

    /* 状态码 */
    private Integer code;

    private String message;

    ScoreEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
