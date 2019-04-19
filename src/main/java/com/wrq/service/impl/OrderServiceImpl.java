package com.wrq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.wrq.commons.ServerResponse;
import com.wrq.config.ParameterConfig;
import com.wrq.dao.*;
import com.wrq.enums.*;
import com.wrq.form.CreateOrderForm;
import com.wrq.pojo.*;
import com.wrq.service.IOrderService;
import com.wrq.utils.EnumUtil;
import com.wrq.utils.KeyUtil;
import com.wrq.vo.OrderListVo;
import com.wrq.vo.OrderVo;
import com.wrq.vo.OrderVoList;
import com.wrq.vo.ShopVo;
import com.wrq.vo.backend.BackendOrderListVo;
import com.wrq.vo.backend.BackendOrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangqian on 2019/4/4.
 */
@Slf4j
@Service(value = "iOrderService")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private ParameterConfig parameterConfig;


    /**
     * 创建订单
     * @param form 订单
     * @param user 用户
     * @return 创建订单
     */
    @Transactional
    public ServerResponse create( CreateOrderForm form, User user) {

        OrderMaster orderMaster = new OrderMaster();

        Integer shopId = form.getShopId();

        String orderPrice = form.getOrderPrice();

        log.info(" orderPrice = {}",orderPrice );

        BigDecimal price = new BigDecimal(orderPrice);

        /* 1. 随机订单号 */

        String orderNo = KeyUtil.genUniqueKey();

        orderMaster.setOrderNo(orderNo);


        /* 2. 订单用户信息 */
        orderMaster.setBuyerEmail(user.getEmail());
        orderMaster.setBuyerId(user.getId());
        orderMaster.setBuyerName(user.getUsername());
        orderMaster.setBuyerPhone(user.getPhone());

        orderMaster.setOrderStatus(OrderStatusEnum.NO_PAY.getCode());


        orderMaster.setPayment( price );
        orderMaster.setShopId(shopId);
        orderMaster.setPaymentType(PayPlatformEnum.ALIPAY.getCode());

        /* 3. 订单主表 */

        int result = orderMasterMapper.insert(orderMaster);

        if ( result <= 0 ){
            return ServerResponse.createByErrorMessage("创建订单失败！");
        }


        /* 4. 订单详情表 */
        OrderItem orderItem = new OrderItem();

        orderItem.setOrderNo(orderNo);


        orderItem.setColorInfoType(form.getBlackOrColor());
        orderItem.setFileId(form.getFileId());
        orderItem.setFileQuantity(form.getFileQuantity());

        orderItem.setSizeInfoType(form.getPageSize());


        orderItem.setPageInfoType(form.getSingleOrDouble());

        orderItem.setCurrentPrice(price);
        orderItem.setUserDes( form.getUserDesc() );
        orderItem.setUserId( user.getId() );

        int insertResult = orderItemMapper.insert(orderItem);

        if ( insertResult <= 0 ){
            return ServerResponse.createByErrorMessage("创建订单详情失败！");
        }

        return ServerResponse.createBySuccess(orderNo);
    }


    /**
     * 点击去支付，跳转至支付页面！
     * @param orderNo
     * @return
     */
    public ServerResponse getOrderBeforePay( User user, String orderNo ) {

        OrderMaster order = orderMasterMapper.selectByUserIdAndOrderNo(user.getId(), orderNo);

        if (order == null) {
            return ServerResponse.createByErrorMessage("用户没有该订单");
        }

        Integer fileId;

        String fileName;

        String fileExtensionName;

        List<OrderItem> orderItemList = orderItemMapper.selectOrderItemByOrderNo(orderNo);

        List<OrderVo> orderVoList = Lists.newArrayList();

        if ( orderItemList.size() == 0 ){
            return ServerResponse.createBySuccess("获取待支付订单明细错误！");
        }

        for(OrderItem orderItem : orderItemList){

            OrderVo orderVo = new OrderVo();

            fileId = orderItem.getFileId();

            File file = fileMapper.selectByPrimaryKey(fileId);
            if ( file == null ){
                return ServerResponse.createBySuccess("获取上传的打印文件失败，请稍后再试！");
            }else {
                fileName = file.getFileName();
            }

            fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
            orderVo.setFileTypeImg( parameterConfig.getImageHost() + fileExtensionName + ".png" );
            orderVo.setFileName(fileName);

            /* 获取当前订单明细的订单是彩印还是黑白 */
            String colorOrBlackResult = EnumUtil.getByCode(orderItem.getColorInfoType(), ColorTypeEnum.class).getMessage();

            orderVo.setColorOrBlack(colorOrBlackResult);


            orderVo.setFileQuantity(orderItem.getFileQuantity());
            orderVo.setOrderPrice(orderItem.getCurrentPrice());

            /* 获取当前订单明细的订单尺寸 */
            String pageSizeResult = EnumUtil.getByCode(orderItem.getSizeInfoType(), PageSizePriceEnum.class).getMessage();

            orderVo.setPageSize(pageSizeResult);

            /* 获取当前订单明细的订单是单页还是双页 */
            String pageTypeResult = EnumUtil.getByCode(orderItem.getPageInfoType(), PageTypeEnum.class).getMessage();

            orderVo.setSingleOrDouble(pageTypeResult);

            orderVoList.add(orderVo);
        }

        OrderVoList orderList = new OrderVoList();

        orderList.setOrderVoList(orderVoList);

        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderNo);

        if ( orderMaster == null ){
            return ServerResponse.createBySuccess("此订单不存在！");
        }

        BigDecimal payment = orderMaster.getPayment();

        String orderStatus = EnumUtil.getByCode(orderMaster.getOrderStatus(), OrderStatusEnum.class).getMessage();

        orderList.setTotalPrice(payment);
        orderList.setOrderNo(orderNo);
        orderList.setPhone(user.getPhone());
        orderList.setOrderStatus( orderStatus );

       return ServerResponse.createBySuccess(orderList);
    }

    /**
     * 获取 订单列表
     * @param userId
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public ServerResponse<PageInfo> getOrderList(Integer userId, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        List<OrderMaster> orderMasterList = orderMasterMapper.selectOrderListByUserId(userId);

        List<OrderListVo> result = assembleOrderListVo(orderMasterList);

        PageInfo pageResult = new PageInfo(orderMasterList);

        pageResult.setList(result);

        return ServerResponse.createBySuccess(pageResult);
    }

    /**
     * 店家端获取订单列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse getOrderListForShop(Integer userId, Integer pageNum, Integer pageSize) {

        Shop shop = shopMapper.selectShopByUserId(userId);

        if ( shop == null ){
            return ServerResponse.createByErrorMessage("您还没有登记店铺信息，请联系官方进行登记！");
        }

        Integer shopId = shop.getId();

        PageHelper.startPage(pageNum, pageSize);

        List<OrderMaster> orderMasterList = orderMasterMapper.selectOrderListByShopId(shopId);

        List<BackendOrderListVo> result = assembleBackendOrderListVo(orderMasterList);

        PageInfo pageResult = new PageInfo(orderMasterList);

        pageResult.setList(result);

        return ServerResponse.createBySuccess(pageResult);
    }

    /**
     * 店家端 根据订单号获取订单详情
     * @param userId
     * @param orderNo
     * @return
     */
    @Override
    public ServerResponse getOrderDetailForBackend( Integer userId, String orderNo ) {

        BackendOrderVo backendOrderVo = new BackendOrderVo();

        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderNo);

        if ( orderMaster == null ){
            return ServerResponse.createBySuccess(" 查询不到此订单 ");
        }

        backendOrderVo.setOrderNo(orderNo);
        backendOrderVo.setPayment(orderMaster.getPayment());
        backendOrderVo.setOrderStatus(EnumUtil.getByCode(orderMaster.getOrderStatus(), OrderStatusEnum.class).getMessage());
        backendOrderVo.setGetKey(orderMaster.getGetKey());
        backendOrderVo.setRefuseReason(orderMaster.getRefuseReason());
        backendOrderVo.setUserEmail(orderMaster.getBuyerEmail());
        backendOrderVo.setUsername(orderMaster.getBuyerName());
        backendOrderVo.setUserPhone(orderMaster.getBuyerPhone());
        backendOrderVo.setCreateTime(orderMaster.getCreateTime());
        backendOrderVo.setPaymentTime(orderMaster.getPaymentTime());
        backendOrderVo.setShopId(orderMaster.getShopId());

        List<OrderItem> orderItemList = orderItemMapper.selectOrderItemByOrderNo(orderNo);

        if ( orderItemList.size() == 0){
            return ServerResponse.createBySuccess(" 查询不到此订单的明细！ ");
        }

        List<OrderVo> orderVoList = Lists.newArrayList();

        for ( OrderItem orderItem : orderItemList ){

            OrderVo orderVo = new OrderVo();

            String fileName;

            String fileExtensionName;

            Integer fileId = orderItem.getFileId();

            File file = fileMapper.selectByPrimaryKey(fileId);

            if ( file == null ){
                return ServerResponse.createBySuccess("获取上传的打印文件失败，请稍后再试！");
            }else {
                fileName = file.getFileName();
            }

            fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);

            orderVo.setFileTypeImg( parameterConfig.getImageHost() + fileExtensionName + ".png" );
            orderVo.setFileName(fileName);

            /* 获取当前订单明细的订单是彩印还是黑白 */
            String colorOrBlackResult = EnumUtil.getByCode(orderItem.getColorInfoType(), ColorTypeEnum.class).getMessage();

            orderVo.setColorOrBlack(colorOrBlackResult);


            orderVo.setFileQuantity(orderItem.getFileQuantity());
            orderVo.setOrderPrice(orderItem.getCurrentPrice());

            /* 获取当前订单明细的订单尺寸 */
            String pageSizeResult = EnumUtil.getByCode(orderItem.getSizeInfoType(), PageSizePriceEnum.class).getMessage();

            orderVo.setPageSize(pageSizeResult);

            /* 获取当前订单明细的订单是单页还是双页 */
            String pageTypeResult = EnumUtil.getByCode(orderItem.getPageInfoType(), PageTypeEnum.class).getMessage();

            orderVo.setSingleOrDouble(pageTypeResult);

            orderVo.setUserDesc(orderItem.getUserDes());

            orderVo.setFileNewName(file.getNewName());

            orderVoList.add(orderVo);
        }

        backendOrderVo.setOrderVoList(orderVoList);

        return ServerResponse.createBySuccess(backendOrderVo);
    }

    /**
     * orderMasterList(数据库订单主表信息) -> OrderListVo集合（个人中心订单列表展示）
     * @param orderMasterList
     * @return
     */
    private List<OrderListVo> assembleOrderListVo( List<OrderMaster> orderMasterList){
        List<OrderListVo> orderList = Lists.newArrayList();
        for(OrderMaster orderMaster : orderMasterList){

            OrderListVo orderListVo = new OrderListVo();

            Integer shopId = orderMaster.getShopId();

            orderListVo.setOrderNo(orderMaster.getOrderNo());
            orderListVo.setGetKey(orderMaster.getGetKey());
            String orderStatus = EnumUtil.getByCode(orderMaster.getOrderStatus(), OrderStatusEnum.class).getMessage();
            orderListVo.setOrderStatus(orderStatus);
            orderListVo.setPayment(orderMaster.getPayment());
            orderListVo.setRefuseReason(orderMaster.getRefuseReason());
            orderListVo.setShopId(shopId);

            Shop shop = shopMapper.selectByPrimaryKey(shopId);

            if (shop == null){
                orderListVo.setShopImg(parameterConfig.getImageHost() + parameterConfig.getShopNotFound());
            }else {
                orderListVo.setShopImg(parameterConfig.getImageHost() + shop.getMiniImg());
            }

            orderListVo.setUpdateTime(orderMaster.getUpdateTime());

            orderList.add(orderListVo);
        }
        return orderList;
    }


    /**
     * 后台订单列表展示 orderMasterList -> BackendOrderListVo
     * @param orderMasterList
     * @return
     */
    private List<BackendOrderListVo> assembleBackendOrderListVo(List<OrderMaster> orderMasterList){
        List<BackendOrderListVo> orderList = Lists.newArrayList();
        for(OrderMaster orderMaster : orderMasterList){

            BackendOrderListVo backendOrderListVo = new BackendOrderListVo();

            backendOrderListVo.setOrderStatus(EnumUtil.getByCode(orderMaster.getOrderStatus(), OrderStatusEnum.class).getMessage());

            backendOrderListVo.setPayment(orderMaster.getPayment());
            backendOrderListVo.setBuyerEmail(orderMaster.getBuyerEmail());
            backendOrderListVo.setBuyerPhone(orderMaster.getBuyerPhone());
            backendOrderListVo.setOrderNo(orderMaster.getOrderNo());
            backendOrderListVo.setCreateTime(orderMaster.getCreateTime());
            backendOrderListVo.setShopId(orderMaster.getShopId());

            orderList.add(backendOrderListVo);

        }
        return orderList;
    }

    /**
     * 店家下载文件时，判断此店家有没有下载文件的权限
     * @param userId
     * @param fileNewName
     * @return
     */
    public ServerResponse checkOrderHasFile(Integer userId, String fileNewName ){

        Shop shop = shopMapper.selectShopByUserId(userId);

        if ( shop == null ){
            return ServerResponse.createByErrorMessage("此用户还未进行店铺登记，请联系官方人员！");
        }

        File file = fileMapper.selectFileByFileNewName(fileNewName);

        if ( file == null ){
            return ServerResponse.createByErrorMessage(" 请求下载的文件无记录 ");
        }

        Integer fileId = file.getId();

        OrderItem orderItem = orderItemMapper.selectByFileId(fileId);

        if ( orderItem == null ){
            return ServerResponse.createByErrorMessage(" 无打印此文件的订单，请确认订单号！ ");
        }

        Integer shopId = shop.getId();

        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderItem.getOrderNo());

        if ( orderMaster == null ){
            return ServerResponse.createByErrorMessage(" 订单明细还在，订单概要信息找不到了！ ");
        }

        if ( (orderMaster.getShopId()) != shopId ){
            return ServerResponse.createByErrorMessage(" 此打印文件不属于当前店铺，请不要访问非本店铺文件！ ");
        }
        return ServerResponse.createBySuccess();
    }

}
