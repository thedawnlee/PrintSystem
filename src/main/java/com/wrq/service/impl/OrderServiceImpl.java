package com.wrq.service.impl;

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
import com.wrq.vo.OrderVo;
import com.wrq.vo.OrderVoList;
import com.wrq.vo.ShopVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

        for(OrderItem orderItem : orderItemList){

            OrderVo orderVo = new OrderVo();

            if ( orderItem == null ){
                return ServerResponse.createBySuccess("获取待支付订单错误！");
            }else {
                fileId = orderItem.getFileId();
            }

            File file = fileMapper.selectByPrimaryKey(fileId);
            if ( file == null ){
                return ServerResponse.createBySuccess("获取上传的打印文件失败，请稍后再试！");
            }else {
                fileName = file.getFileName();
            }

            fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
            orderVo.setFileTypeImg( parameterConfig.getImageHost()  + "/" + fileExtensionName + ".png" );
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

        orderList.setTotalPrice(payment);
        orderList.setOrderNo(orderNo);
        orderList.setPhone(user.getPhone());

       return ServerResponse.createBySuccess(orderList);
    }

}
