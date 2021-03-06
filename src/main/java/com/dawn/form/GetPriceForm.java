package com.dawn.form;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class GetPriceForm {

    private Integer shopId;

    @NotNull(message = "请上传打印文件后操作")
    private Integer pageCount;

    @NotNull(message = "请填写打印文件份数")
    private Integer fileQuantity;

    @NotNull(message = "请选择单页/双页参数")
    private  Integer singleOrDouble;

    @NotNull(message = "请选择黑白/彩打参数")
    private Integer blackOrColor;

    @NotNull(message = "文件的尺寸参数必填")
    private Integer pageSize;
}
