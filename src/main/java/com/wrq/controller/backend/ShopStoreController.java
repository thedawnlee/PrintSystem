package com.wrq.controller.backend;

import com.wrq.commons.Const;
import com.wrq.commons.ResponseCode;
import com.wrq.commons.ServerResponse;
import com.wrq.pojo.User;
import com.wrq.service.IFileService;
import com.wrq.service.IOrderService;
import com.wrq.service.IShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by wangqian on 2019/4/16.
 */
@Controller
@RequestMapping("/store/shop")
@Slf4j
public class ShopStoreController {


    @Autowired
    private IShopService iShopService;

    @Autowired
    private IFileService iFileService;


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


    /**
     * 店家端 店铺信息
     * @param session
     * @return
     */
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse detail( HttpSession session ){

        User user = (User)session.getAttribute(Const.CURRENT_USER);

        if(user == null){
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getDesc());
        }

        if(user.getRole() != Const.Role.ROLE_STORE){
            return ServerResponse.createByErrorMessage("不是店主，无法操作！");
        }

        return  iShopService.getShopDetailByUserId( user.getId() );
    }


    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse mainImage(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request){

        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请先登陆");
        }

        if ( user.getRole() != Const.Role.ROLE_STORE){
            return ServerResponse.createByErrorMessage("当前用户未进行店面登记，请核实");
        }

        String path = request.getSession().getServletContext().getRealPath("upload");


        return iFileService.uploadImg( file, path );
    }

}
