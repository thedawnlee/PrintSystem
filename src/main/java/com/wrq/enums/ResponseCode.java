package com.wrq.enums;

public enum ResponseCode {

    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT"),
    NEED_LOGON_FOR_CREATE(3, "申请打印前，请先登陆！")
    ;

    private  final  int code;
    private  final  String desc;

    ResponseCode(int code, String desc){
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
