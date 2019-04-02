package com.wrq.enums;

/**
 * Created by wangqian on 2019/4/1.
 */
public enum ServiceNotExistEnum {

    SERVICE_NOT_EXIST("无服务"),
    PAGE_SIZE_SERVICE_NOT_EXIST("不提供该尺寸打印服务"),
    COLORFUL_SERVICE_NOT_EXIST("不提供彩印服务")
    ;

    /* 状态码 */


    public String getMessage() {
        return message;
    }

    private String message;

    ServiceNotExistEnum(String message) {
        this.message = message;
    }

}
