package com.dawn.vo;

import lombok.Data;

import java.util.List;


@Data
public class FormVo {

    private Integer userId;

    private String username;

    private String phone;

    private Boolean hasSingle;

    private Boolean hasBlack;

    private Boolean hasColorful;

    private Boolean hasDouble;

    private List pageSizeList;

}
