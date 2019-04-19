package com.wrq.commons;

public enum  ResponseCode {

    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT"),
    NEED_LOGON_FOR_CREATE(10, "登陆后即可申请打印"),
    NEED_LOGON_FOR_USER_INFO(10, "登陆后即可查看个人中心"),
    NEED_LOGON_FOR_ORDER_DETAIL_INFO(10, "登陆后即可查看订单详情"),
    NEED_LOGON_FOR_ORDER_LIST(10, "登陆后即可查看订单列表"),
    ;

    private  final  int code;
    private  final  String desc;

    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public  int  getCode(){
        return code;
    }
    public  String getDesc(){
        return desc;
    }
}
