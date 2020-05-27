package com.dawn.exception;


import com.dawn.enums.ResultEnum;

public class PrintException extends RuntimeException {

    private Integer code;

    public PrintException(ResultEnum resultEnum) {

        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public PrintException(Integer code, String message) {

        super(message);

        this.code = code;
    }
}
