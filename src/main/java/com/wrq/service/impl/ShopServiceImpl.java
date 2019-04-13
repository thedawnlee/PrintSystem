package com.wrq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.wrq.commons.ServerResponse;
import com.wrq.config.ParameterConfig;
import com.wrq.dao.ShopMapper;
import com.wrq.dao.UserMapper;
import com.wrq.pojo.Shop;
import com.wrq.pojo.User;
import com.wrq.service.IShopPriceService;
import com.wrq.service.IShopService;
import com.wrq.vo.DetailVo;
import com.wrq.vo.OtherShopVo;
import com.wrq.vo.ShopVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by wangqian on 2019/3/30.
 */
@Service(value = "iShopService")
@Slf4j
public class ShopServiceImpl implements IShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ParameterConfig paramConfig;

    @Autowired
    private IShopPriceService iShopPriceService;

    /**
     * 得到店面列表-评分排序
     * @param pageNum 页面
     * @param pageSize 个数
     * @return 分页店面列表
     */
    @Override
    public ServerResponse<PageInfo> getShopListByTypeSort(int pageNum, int pageSize, String type) {
        PageHelper.startPage(pageNum,pageSize);
        List<Shop> shopList = shopMapper.selectShopListByTypeSort(type);
        List<ShopVo> shopVoList = assembleShopVoList(shopList);
        PageInfo pageResult = new PageInfo(shopList);
        pageResult.setList(shopVoList);
        return  ServerResponse.createBySuccess(pageResult);
    }

    /**
     * 得到所有店面，默认按照 创建时间
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse<PageInfo> getShopList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Shop> shopList = shopMapper.selectAllShopByCredit();
        List<ShopVo> shopVoList = assembleShopVoList(shopList);
        PageInfo pageResult = new PageInfo(shopList);
        pageResult.setList(shopVoList);
        return  ServerResponse.createBySuccess(pageResult);
    }

    /**
     * shopList -> shopVoList
     * @param shopList shopList
     * @return shopVoList
     */
    private List<ShopVo> assembleShopVoList(List<Shop> shopList){
        List<ShopVo> shopVoList = Lists.newArrayList();
        for(Shop shop : shopList){
            ShopVo shopVo = new ShopVo();
            shopVo.setShopId(shop.getId());
            shopVo.setShopName(shop.getShopName());
            shopVo.setCredit(shop.getCredit());
            shopVo.setDealNum(shop.getDealNum());
            shopVo.setShopDescription(shop.getShopDescription());
            shopVo.setNormalDouble(iShopPriceService.getNormalDouble(shop.getId()));
            shopVo.setNormalSingle(iShopPriceService.getNormalSingle(shop.getId()));
            shopVo.setColorfulDouble(iShopPriceService.getColorfulDouble(shop.getId()));
            shopVo.setColorfulSingle(iShopPriceService.getColorfulSingle(shop.getId()));
            shopVo.setStatus(shop.getStatus());
            shopVo.setImgAddress(paramConfig.getImageHost() + shop.getSubImg());
            shopVoList.add(shopVo);
        }
        return shopVoList;
    }


    /**
     * 获取店铺详情
     * @param shopId 店铺ID
     * @return 获取店铺情况
     */
    @Override
    public ServerResponse<DetailVo> getShopDetailById(Integer shopId) {

        Shop shop = shopMapper.selectByPrimaryKey(shopId);
        if (shop == null){
            return ServerResponse.createByErrorMessage("此店铺不存在！");
        }
        User user = userMapper.selectByPrimaryKey(shop.getOwnerId());
        DetailVo detailVo = new DetailVo();

        BeanUtils.copyProperties(shop, detailVo);
        detailVo.setMiniImage(paramConfig.getImageHost() + shop.getMiniImg());
        detailVo.setOwnerAddress(user.getAnswer());
        detailVo.setOwnerPhone(user.getPhone());
        detailVo.setOwnerEmail(user.getEmail());
        detailVo.setShopId(shop.getId());
        return ServerResponse.createBySuccess(detailVo);
    }

    /**
     * 获取其他店铺，除了此shopId
     * @param shopId 除了shopId
     * @return 其他店铺
     */
    @Override
    public ServerResponse<List<OtherShopVo>> getOtherShopByShopId(Integer shopId) {

        List<Shop> shops = shopMapper.selectOtherShopSortByCredit(shopId);
        if (shops == null){
            return ServerResponse.createByErrorMessage("没有其他店铺推荐！");
        }else {
            List<OtherShopVo> otherShopVos = assembleOtherShopVoList(shops);
            return ServerResponse.createBySuccess(otherShopVos);
        }
    }


    /**
     * List<Shop> -> List<OtherShopVo>
     * @param shopList shop详细信息列表
     * @return
     */
    private List<OtherShopVo> assembleOtherShopVoList ( List<Shop> shopList ) {
        List<OtherShopVo> otherShopList = Lists.newArrayList();
        for(Shop shop : shopList){
            OtherShopVo otherShopVo = new OtherShopVo();

            otherShopVo.setCredit(shop.getCredit());
            otherShopVo.setDealNum(shop.getDealNum());
            otherShopVo.setShopAddress(shop.getShopAddress());
            otherShopVo.setShopId(shop.getId());
            otherShopVo.setShopName(shop.getShopName());
            otherShopVo.setMiniAddress( paramConfig.getImageHost() + shop.getMiniImg());

            otherShopList.add(otherShopVo);
        }
        return otherShopList;
    }

}
