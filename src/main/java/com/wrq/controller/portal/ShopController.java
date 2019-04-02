package com.wrq.controller.portal;

import com.wrq.commons.ServerResponse;
import com.wrq.service.IShopPriceService;
import com.wrq.service.IShopService;
import com.wrq.vo.DetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


/**
 * Created by wangqian on 2019/3/30.
 */
@Controller
@RequestMapping("/shop/")
@Slf4j
public class ShopController {

    @Autowired
    private IShopService iShopService;

    @Autowired
    private IShopPriceService iShopPriceService;


    /**
     * 根据 评分 查询接单店铺列表
     * @param pageNum 第几页
     * @param pageSize 一页多少
     * @return 店铺列表
     */
    @RequestMapping(value = "shop_list.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse list(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
       return iShopService.getShopListByCreditSort(pageNum, pageSize);
    }

    /**
     * 当前店铺 详情
     * @param id 店铺ID
     * @return 店铺详情
     */
    @GetMapping("detail/{id}")
    @ResponseBody
    public ModelAndView detail(@PathVariable("id") Integer id, Map<String, Object> map) {

        ServerResponse<DetailVo> detail = iShopService.getShopDetailById(id);
        if ( detail.isSuccess() ){
            map.put("shopInfo", detail.getData());
            return new ModelAndView("portal/detail", map);
        }else {
            map.put("msg", detail.getMsg());
            map.put("url", "/index");
            return new ModelAndView("portal/common/page/error", map);
        }
    }

    /**
     * 获得当前店铺的价格参数
     * @param id 店铺ID
     * @return 店铺价格情况
     */
    @GetMapping("shop_price.do")
    @ResponseBody
    public ServerResponse priceParam(@RequestParam(value = "id") int id) {
        return ServerResponse.createBySuccess(iShopPriceService.getPriceVoByShopId(id));
    }


    /**
     * 除了此 id 店铺外的所有店铺
     * @param id 除外的ID
     * @return 其他五个店铺，按照信用排名
     */
    @GetMapping("other_shop.do")
    @ResponseBody
    public ServerResponse otherShop(@RequestParam(value = "id") int id) {
        return iShopService.getOtherShopByShopId(id);
    }

}
