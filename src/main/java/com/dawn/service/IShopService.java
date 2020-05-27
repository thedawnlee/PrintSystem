package com.dawn.service;

import com.github.pagehelper.PageInfo;
import com.dawn.commons.ServerResponse;
import com.dawn.form.ShopForm;
import com.dawn.form.ShopPriceForm;
import com.dawn.vo.DetailVo;
import com.dawn.vo.OtherShopVo;

import java.util.List;


public interface IShopService {

    ServerResponse<PageInfo> getShopListByTypeSort(int pageNum, int pageSize, String type);

    ServerResponse<DetailVo> getShopDetailById(Integer shopId);

    ServerResponse<List<OtherShopVo>> getOtherShopByShopId(Integer shopId);

    ServerResponse<PageInfo> getShopList(int pageNum, int pageSize);

    ServerResponse getShopInfoByUserId(Integer userId);

    ServerResponse getShopDetailByUserId(Integer userId);

    ServerResponse updateShopInfo(Integer userId, ShopForm form);

    ServerResponse changeShopStatus(Integer userId);

    ServerResponse creditShop( Integer userId, String star, String orderNo );

    ServerResponse getShopPriceByUserId(Integer userId);

    ServerResponse updatePrice(ShopPriceForm form, Integer userId);

}
