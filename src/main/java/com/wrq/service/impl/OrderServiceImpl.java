package com.wrq.service.impl;

import com.google.common.collect.Lists;
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


        orderItem.setColorInfoType(form.getBlackOrColor());
        orderItem.setFileId(form.getFileId());
        orderItem.setFileQuantity(form.getFileQuantity());

        orderItem.setSizeInfoType(form.getPageSize());


        orderItem.setPageInfoType(form.getSingleOrDouble());

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
     * 点击去支付，跳转至支付页面！
     * @param orderNo
     * @return
     */
    public ServerResponse getOrderBeforePay( String orderNo ) {

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
            orderVo.setFileType( fileExtensionName );
            orderVo.setOrderNo( orderNo );
            orderVo.setFileName(fileName);

            orderVo.setColorOrBlack(orderItem.getColorInfoType());
            orderVo.setFileQuantity(orderItem.getFileQuantity());
            orderVo.setOrderPrice(orderItem.getTotalPrice());
            orderVo.setPageSize(orderItem.getSizeInfoType());
            orderVo.setSingleOrDouble(orderItem.getPageInfoType());

            orderVoList.add(orderVo);
        }

       return ServerResponse.createBySuccess(orderVoList);

    }

}
