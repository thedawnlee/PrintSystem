package com.wrq.enums;

/**
 * Created by wangqian on 2019/4/8.
 */
public enum PayPlatformEnum {

        ALIPAY(0,"支付宝");
        private  String value;
        private  int code;
        PayPlatformEnum(int code,String value){
            this.code   = code;
            this.value  = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

}
