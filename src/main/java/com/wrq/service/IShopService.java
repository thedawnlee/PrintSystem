package com.wrq.service;

import com.github.pagehelper.PageInfo;
import com.wrq.commons.ServerResponse;
import com.wrq.vo.DetailVo;
import com.wrq.vo.OtherShopVo;

import java.util.List;

/**
 * Created by wangqian on 2019/3/30.
 */
public interface IShopService {

    ServerResponse<PageInfo> getShopListByCreditSort(int pageNum, int pageSize);

    ServerResponse<PageInfo> getShopListByDealNumSort(int pageNum, int pageSize);

    ServerResponse<PageInfo> getShopListByCreateTimeSort(int pageNum, int pageSize);

    ServerResponse<DetailVo> getShopDetailById(Integer shopId);

    ServerResponse<List<OtherShopVo>> getOtherShopByShopId(Integer shopId);

}
