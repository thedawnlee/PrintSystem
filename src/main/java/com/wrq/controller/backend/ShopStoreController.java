package com.wrq.controller.backend;

import com.wrq.commons.Const;
import com.wrq.commons.ResponseCode;
import com.wrq.commons.ServerResponse;
import com.wrq.pojo.User;
import com.wrq.service.IOrderService;
import com.wrq.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by wangqian on 2019/4/16.
 */
@Controller
@RequestMapping("/store/shop")
public class ShopStoreController {


    @Autowired
    private IShopService iShopService;


    /**
     * 店家端 页面加载的时候获取店铺信息
     * @param session
     * @return
     */
    @RequestMapping(value = "get_shop_info.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse list( HttpSession session ){

        User user = (User)session.getAttribute(Const.CURRENT_USER);

        if(user == null){
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getDesc());
        }

        if(user.getRole() != Const.Role.ROLE_STORE){
            return ServerResponse.createByErrorMessage("不是店主，无法操作！");
        }

        return  iShopService.getShopInfoByUserId( user.getId() );
    }
}
