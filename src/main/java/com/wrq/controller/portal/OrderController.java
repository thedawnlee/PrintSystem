package com.wrq.controller.portal;

import com.wrq.commons.Const;
import com.wrq.commons.ResponseCode;
import com.wrq.commons.ServerResponse;
import com.wrq.form.CreateOrderForm;
import com.wrq.form.GetPriceForm;
import com.wrq.pojo.User;
import com.wrq.service.IOrderService;
import com.wrq.service.IShopPriceService;
import com.wrq.service.IShopService;
import com.wrq.vo.DetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangqian on 2019/4/4.
 */
@Controller
@RequestMapping("/order/")
@Slf4j
public class OrderController {

    @Autowired
    private IShopService iShopService;

    @Autowired
    private IShopPriceService iShopPriceService;

    @Autowired
    private IOrderService iOrderService;

    /**
     * 跳转 创建订单 页面
     * @param id 选择的店铺ID
     * @param map map
     * @param session session
     * @return 强制登陆，或者跳转
     */
    @RequestMapping(value = "create/{id}", method = RequestMethod.GET)
    public ModelAndView create(@PathVariable("id") Integer id, Map<String, Object> map, HttpSession session) {

        log.info("请求了 /order/create 接口， 店铺 ID = {}", id);

        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            map.put("msg", ResponseCode.NEED_LOGON_FOR_CREATE.getDesc());
            map.put("url", "/order/create/" + id);
            return new ModelAndView("portal/login" , map);
        }else {
            log.info("请求了 /order/create 接口 的 else 分支");
            ServerResponse<DetailVo> detail = iShopService.getShopDetailById(id);
            map.put("shopInfo", detail.getData());
            return new ModelAndView("portal/create" , map);
        }
    }

    /**
     * 获取 此店铺创建订单表单的相关参数
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "get_form_info.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse form(@RequestParam("id") Integer id, HttpSession session) {

        log.info("请求了 /order/get_form_info.do 接口， 店铺 ID = {}", id);

        User user = (User)session.getAttribute(Const.CURRENT_USER);

        if(user == null){
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGON_FOR_CREATE.getDesc());
        }else {
            return ServerResponse.createBySuccess(iShopPriceService.getFormVoByShopId(id, user));
        }
    }

    /**
     * 当用户选择创建订单表单参数时，获取价格！
     * @param form 上传参数
     * @param bindingResult 参数验证
     * @param session session
     * @return 价格
     */
    @RequestMapping(value = "get_price.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse price(@Valid GetPriceForm form , BindingResult bindingResult, HttpSession session) {

        log.info("请求了 /order/get_price.do.do 接口");

        if ( bindingResult.hasErrors() ) {
            return ServerResponse.createByErrorMessage(bindingResult.getFieldError().getDefaultMessage());
        }

        User user = (User)session.getAttribute(Const.CURRENT_USER);

        if(user == null){
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGON_FOR_CREATE.getDesc());
        }else {
            return  iShopPriceService.getOrderPrice(form);
        }
    }

    /**
     * 上传文件页面点击 去支付 创建订单！
     * @param form
     * @param bindingResult
     * @param session
     * @return
     */
    @RequestMapping(value = "create_order.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse createOrder(@Valid CreateOrderForm form , BindingResult bindingResult, HttpSession session) {

        log.info("请求了 /order/create_order.do 接口");

        if ( bindingResult.hasErrors() ) {
            return ServerResponse.createByErrorMessage(bindingResult.getFieldError().getDefaultMessage());
        }

        User user = (User)session.getAttribute(Const.CURRENT_USER);

        if(user == null){
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGON_FOR_CREATE.getDesc());
        }else {
            return ServerResponse.createBySuccess(iOrderService.create(form, user));
        }
    }

    /**
     * 当创建订单完毕，去支付页面时，通过此方法跳转！
     * @param orderNo
     * @param session
     * @param map
     * @return
     */
    @RequestMapping(value = "get_order.do", method = RequestMethod.GET)
    public ModelAndView getOrder(@RequestParam("orderNo") String orderNo, HttpSession session, Map<String, Object> map) {

        log.info("请求了 /order/get_order.do 接口");

        User user = (User)session.getAttribute(Const.CURRENT_USER);

        if(user == null){
            map.put("msg", ResponseCode.NEED_LOGON_FOR_CREATE.getDesc());
            map.put("url", "/order/get_order.do?orderNo=" + orderNo);
            return new ModelAndView("portal/login" , map);
        }else {

            ServerResponse result = iOrderService.getOrderBeforePay(user, orderNo);

            if ( result.isSuccess() ) {
                map.put("orderInfo", result.getData());
                return new ModelAndView("portal/pay" , map);
            }else {
                map.put("msg", result.getMsg());
                map.put("url", "/index");
                return new ModelAndView("portal/common/page/error" , map);
            }
        }
    }

}
