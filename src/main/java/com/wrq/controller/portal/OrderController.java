package com.wrq.controller.portal;

import com.wrq.commons.Const;
import com.wrq.commons.ResponseCode;
import com.wrq.commons.ServerResponse;
import com.wrq.pojo.User;
import com.wrq.service.IShopPriceService;
import com.wrq.service.IShopService;
import com.wrq.vo.DetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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

}
