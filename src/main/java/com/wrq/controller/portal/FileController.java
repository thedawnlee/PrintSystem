package com.wrq.controller.portal;

import com.wrq.commons.Const;
import com.wrq.commons.ResponseCode;
import com.wrq.commons.ServerResponse;
import com.wrq.pojo.User;
import com.wrq.service.IFileService;
import com.wrq.utils.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;

/**
 * Created by wangqian on 2019/4/6.
 */
@Controller
@RequestMapping("/file/")
@Slf4j
public class FileController {


    @Autowired
    private IFileService iFileService;

    /**
     * 文件上传，上传到upload然后放到ftp服务器
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "upload.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request){

        log.info(" 请求了 upload.do 接口， file = {}", file);

        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请先登陆");
        }

        String path = request.getSession().getServletContext().getRealPath("upload");

        return iFileService.upload(file, path, user.getId());
    }

    /***
     * @param file file 新名字
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "download", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse download(String file, HttpSession session,  HttpServletRequest request, HttpServletResponse response){

        User user = (User)session.getAttribute(Const.CURRENT_USER);

        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请先登陆");
        }

        String path = request.getSession().getServletContext().getRealPath("upload");

        ServerResponse result = null;

        try {
            result = iFileService.userDownload(path, file, user.getId(), response);
        } catch (UnsupportedEncodingException e) {
            log.error(" 文件名编码失败 ");
        }

        if ( !result.isSuccess() ){
            return result;
        }

        return ServerResponse.createBySuccess("下载成功");
    }

    /**
     * 个人中心 文件列表
     * @param pageNum
     * @param pageSize
     * @param session
     * @return
     */
    @RequestMapping(value = "get_file_list.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getFile(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,HttpSession session){

        log.info("请求了 get_file_list 接口");

        User user = (User)session.getAttribute(Const.CURRENT_USER);

        if(user != null){
            return iFileService.getFileList(user.getId(), pageNum, pageSize);
        }
        return  ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
    }

    @RequestMapping(value = "not_share.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse notShareFile(HttpSession session){

        log.info("请求了 not_share 接口");

        User user = (User)session.getAttribute(Const.CURRENT_USER);

        if(user != null){
            return iFileService.getNotShareFileList(user.getId());
        }
        return  ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
    }


}
