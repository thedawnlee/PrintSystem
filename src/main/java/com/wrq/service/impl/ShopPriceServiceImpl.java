package com.wrq.service.impl;

import com.google.common.collect.Lists;
import com.wrq.dao.BonusMapper;
import com.wrq.dao.ColorMapper;
import com.wrq.dao.PageSizeMapper;
import com.wrq.dao.SingleDoubleMapper;
import com.wrq.enums.ColorTypeEnum;
import com.wrq.enums.PageSizePriceEnum;
import com.wrq.enums.PageTypeEnum;
import com.wrq.enums.ServiceNotExistEnum;
import com.wrq.pojo.Bonus;
import com.wrq.pojo.Color;
import com.wrq.pojo.PageSize;
import com.wrq.pojo.SingleDouble;
import com.wrq.service.IShopPriceService;
import com.wrq.utils.BigDecimalUtil;
import com.wrq.vo.PriceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by wangqian on 2019/3/30.
 */
@Service("iShopPriceStandard")
public class ShopPriceServiceImpl implements IShopPriceService {

    @Autowired
    private ColorMapper colorMapper;

    @Autowired
    private PageSizeMapper pageSizeMapper;

    @Autowired
    private SingleDoubleMapper singleDoubleMapper;

    @Autowired
    private BonusMapper bonusMapper;

    @Override
    public BigDecimal getNormalDouble(Integer shopId) {

        /* 获取此店铺黑白的价格 */
        Color blackOrWhite = colorMapper.selectBlackOrColorByShopId(shopId, ColorTypeEnum.BLACK.getCode());
        BigDecimal normalPrice = blackOrWhite.getPrice();

        /* 获取此店铺双页的比例系数 */
        SingleDouble doublePage = singleDoubleMapper.selectSingleOrDoubleByShopId(shopId, PageTypeEnum.DOUBLE.getCode());
        String index = doublePage.getVariable();

        /* 黑白价格乘积，比如：黑白价格 1 元，双页面的比例系数为：1.5，则黑白双页面价格：1 * 1.5  */
        BigDecimal price = BigDecimalUtil.mul(normalPrice.doubleValue(), Double.valueOf(index));

        return price;
    }

    @Override
    public BigDecimal getNormalSingle(Integer shopId) {

        /* 获取此店铺黑白的价格 */
        Color blackOrWhite = colorMapper.selectBlackOrColorByShopId(shopId, ColorTypeEnum.BLACK.getCode());
        BigDecimal price = blackOrWhite.getPrice();

        return price;
    }

    @Override
    public BigDecimal getColorfulDouble(Integer shopId) {

        /* 获取此店铺彩色的价格 */
        Color colorful = colorMapper.selectBlackOrColorByShopId(shopId, ColorTypeEnum.COLORFUL.getCode());
        BigDecimal colorfulPrice = colorful.getPrice();

        /* 获取此店铺双页的比例系数 */
        SingleDouble doublePage = singleDoubleMapper.selectSingleOrDoubleByShopId(shopId, PageTypeEnum.DOUBLE.getCode());
        String index = doublePage.getVariable();

        /* 彩色价格乘积，比如：彩色价格 2 元，双页面的比例系数为：1.5，则黑白双页面价格：2 * 1.5  */
        BigDecimal price = BigDecimalUtil.mul(colorfulPrice.doubleValue(), Double.valueOf(index));

        return price;
    }

    @Override
    public BigDecimal getColorfulSingle(Integer shopId) {

        /* 获取此店铺彩色的价格 */
        Color colorful = colorMapper.selectBlackOrColorByShopId(shopId, ColorTypeEnum.COLORFUL.getCode());
        BigDecimal price = colorful.getPrice();

        return price;
    }

    @Override
    public PriceVo getPriceVoByShopId(Integer shopId) {

        PriceVo priceVo = new PriceVo();

        priceVo.setShopId(shopId);

        /* Bonus */
        Bonus bonus = bonusMapper.selectBonusByShopId(shopId);
        if ( bonus == null){
            priceVo.setBonusDescription("此店铺没有优惠活动！");
        }else {
            priceVo.setBonusThreshold(bonus.getThreshold());
            priceVo.setBonusDescription(bonus.getDescription());
            priceVo.setBonusValue(bonus.getBonus());
        }

        /* OtherSizePrice */

        ArrayList sizePriceList = Lists.newArrayList();
        for ( PageSizePriceEnum each: PageSizePriceEnum.values() ){
            PageSize result = pageSizeMapper.getPageSizeByShopIdAndSize(shopId, each.getCode());
            if (result == null){
                sizePriceList.add(ServiceNotExistEnum.PAGE_SIZE_SERVICE_NOT_EXIST.getMessage());
            }else {
                sizePriceList.add(result.getVariable());
            }
        }
        priceVo.setOtherSizePrice(sizePriceList);


        /* 彩印黑白、单双页 比例系数 */
        Color colorful = colorMapper.selectBlackOrColorByShopId(shopId, ColorTypeEnum.COLORFUL.getCode());

        SingleDouble doublePage = singleDoubleMapper.selectSingleOrDoubleByShopId(shopId, PageTypeEnum.DOUBLE.getCode());

        SingleDouble singlePage = singleDoubleMapper.selectSingleOrDoubleByShopId(shopId, PageTypeEnum.SINGLE.getCode());

        priceVo.setColorfulVariable(colorful.getPrice().toString());
        priceVo.setDoubleVariable(doublePage.getVariable());

        BigDecimal doubleColorfulVariable = BigDecimalUtil.mul((colorful.getPrice().doubleValue()), (Double.valueOf(doublePage.getVariable())));

        priceVo.setDoubleColorfulVariable(doubleColorfulVariable.toString());

        priceVo.setNormalSingle(getNormalSingle(shopId));
        priceVo.setColorfulSingle(getColorfulSingle(shopId));
        priceVo.setNormalDouble(getNormalDouble(shopId));
        priceVo.setColorfulDouble(getColorfulDouble(shopId));

        return priceVo;
    }
}
