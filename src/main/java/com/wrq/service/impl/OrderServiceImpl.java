package com.wrq.service.impl;

import com.wrq.commons.ServerResponse;
import com.wrq.dao.*;
import com.wrq.enums.OrderStatusEnum;
import com.wrq.enums.PayPlatformEnum;
import com.wrq.enums.PayStatusEnum;
import com.wrq.form.CreateOrderForm;
import com.wrq.pojo.*;
import com.wrq.service.IOrderService;
import com.wrq.utils.KeyUtil;
import com.wrq.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

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
    private PageSizeMapper pageSizeMapper;

    @Autowired
    private SingleDoubleMapper singleDoubleMapper;

    @Autowired
    private ColorMapper colorMapper;

    @Autowired
    private FileMapper fileMapper;

    /**
     * 创建订单
     * @param form 订单
     * @param user 用户
     * @return 创建订单
     */
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

        orderMaster.setOrderStatus(OrderStatusEnum.NOY_PAY.getCode());





        orderMaster.setPayment( price );
        orderMaster.setShopId(shopId);
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setPaymentType(PayPlatformEnum.ALIPAY.getCode());

        /* 3. 订单主表 */

        int result = orderMasterMapper.insert(orderMaster);

        if ( result <= 0 ){
            return ServerResponse.createByErrorMessage("创建订单失败！");
        }


        /* 4. 订单详情表 */
        OrderItem orderItem = new OrderItem();

        orderItem.setOrderNo(orderNo);

        Color color = colorMapper.selectBlackOrColorByShopId(shopId, form.getBlackOrColor());

        orderItem.setColorInfoId(color.getId());
        orderItem.setFileId(form.getFileId());
        orderItem.setFileQuantity(form.getFileQuantity());

        PageSize pageSize = pageSizeMapper.getPageSizeByShopIdAndSize(shopId, form.getPageSize());
        orderItem.setSizeInfoId(pageSize.getId());


        SingleDouble singleDouble = singleDoubleMapper.selectSingleOrDoubleByShopId(shopId, form.getSingleOrDouble());
        orderItem.setPageInfoId(singleDouble.getId());

        orderItem.setTotalPrice(price);
        orderItem.setUserDes( form.getUserDesc() );
        orderItem.setUserId( user.getId() );

        int insertResult = orderItemMapper.insert(orderItem);

        if ( insertResult <= 0 ){
            return ServerResponse.createByErrorMessage("创建订单详情失败！");
        }

        return ServerResponse.createBySuccess(orderNo);
    }


    /**
     * 创建订单之后跳转值支付页面获取订单的信息
     * @param form
     * @param user
     * @return
     */
    public ServerResponse getOrderBeforePay( CreateOrderForm form, User user) {

       return null;

    }


}
