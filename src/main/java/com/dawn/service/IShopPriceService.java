package com.dawn.service;

import com.dawn.commons.ServerResponse;
import com.dawn.form.GetPriceForm;
import com.dawn.pojo.User;
import com.dawn.vo.FormVo;
import com.dawn.vo.PriceVo;

import java.math.BigDecimal;


public interface IShopPriceService {

    BigDecimal getNormalDouble (Integer shopId);

    BigDecimal getNormalSingle (Integer shopId);

    BigDecimal getColorfulDouble (Integer shopId);

    BigDecimal getColorfulSingle (Integer shopId);

    PriceVo getPriceVoByShopId (Integer shopId);

    FormVo getFormVoByShopId (Integer shopId, User user);

    ServerResponse getOrderPrice(GetPriceForm form);

}
