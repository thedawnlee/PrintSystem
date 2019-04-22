package com.wrq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.wrq.commons.ServerResponse;
import com.wrq.config.ParameterConfig;
import com.wrq.dao.FileMapper;
import com.wrq.dao.ShareMapper;
import com.wrq.dao.UserMapper;
import com.wrq.enums.ShareEnum;
import com.wrq.enums.ShareStatusEnum;
import com.wrq.enums.TagNameEnum;
import com.wrq.form.ShareCreateForm;
import com.wrq.pojo.File;
import com.wrq.pojo.Share;
import com.wrq.pojo.Shop;
import com.wrq.pojo.User;
import com.wrq.service.IShareService;
import com.wrq.utils.DateTimeUtil;
import com.wrq.utils.EnumUtil;
import com.wrq.utils.ShareScoreUtil;
import com.wrq.vo.ShareListVo;
import com.wrq.vo.ShopVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangqian on 2019/4/21.
 */
@Service(value = "iShareService")
@Slf4j
public class ShareServiceImpl implements IShareService {


    @Autowired
    private ShareMapper shareMapper;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private ParameterConfig parameterConfig;

    @Autowired
    private UserMapper userMapper;


    @Override
    public ServerResponse insertShareRecode(ShareCreateForm shareCreateForm, User user) {

        File file = fileMapper.selectByPrimaryKey(shareCreateForm.getFileId());

        if ( file == null ){
            return ServerResponse.createByErrorMessage("找不到需要分享的文件，请重新上传！");
        }

        // 1. 设置积分

        int score = ShareScoreUtil.getScore(file.getPageNum());

        // 2.写入数据库

        int result = fileMapper.updateFileScoreAndStatus(shareCreateForm.getFileId(), score);

        if ( result <= 0 ){
            return ServerResponse.createByErrorMessage("写入文件积分失败，请稍后再试！");
        }

        // 3. share表
        Share share = new Share();


        share.setUserId(user.getId());
        share.setTitle(shareCreateForm.getTitle());
        share.setDescription(shareCreateForm.getDesc());
        share.setViewNum("1");
        share.setContent(shareCreateForm.getRichText());
        share.setDownloadNum(0);
        share.setFileId(shareCreateForm.getFileId());
        share.setIsDelete(ShareStatusEnum.NOT_DELETE.getCode());
        share.setIsHot(ShareStatusEnum.NOT_HOT.getCode());
        share.setIsTop(ShareStatusEnum.NOT_TOP.getCode());
        share.setTag(String.valueOf( EnumUtil.getByCode(shareCreateForm.getTagValue(), TagNameEnum.class).getCode() ));

        int insert = shareMapper.insert(share);

        if ( insert <= 0 ){
            return ServerResponse.createByErrorMessage("创建分享失败，请稍后再试！");
        }

        return ServerResponse.createBySuccessMessage("分享成功！");
    }

    /**
     * 获取全部分享的列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse getShareList(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum,pageSize);

        List<Share> shareList = shareMapper.selectShareList();

        List<ShareListVo> shareListVoList = assembleShareListVo(shareList);

        PageInfo pageResult = new PageInfo(shareList);

        pageResult.setList(shareListVoList);

        return  ServerResponse.createBySuccess(pageResult);
    }


    private List<ShareListVo> assembleShareListVo(List<Share> shareList){

        List<ShareListVo> shareListVo = Lists.newArrayList();

        for(Share share : shareList){

            ShareListVo shareVo = new ShareListVo();

            shareVo.setTitle(share.getTitle());
            shareVo.setCreateTime(DateTimeUtil.dateToStr(share.getCreateTime(), "yyyy-MM-dd"));
            shareVo.setDesc(share.getDescription());

            Integer userId = share.getUserId();

            User user = userMapper.selectByPrimaryKey(userId);

            if ( user == null ){
                shareVo.setHeadImg("");
            }else {
                shareVo.setHeadImg(parameterConfig.getImageHost() + user.getHeaderPic());
            }

            File file = fileMapper.selectByPrimaryKey(share.getFileId());

            shareVo.setShareId(share.getId());
            shareVo.setIntegral(file.getIntegral());
            shareVo.setTagName(EnumUtil.getByCode(Integer.parseInt(share.getTag()), TagNameEnum.class).getMessage());
            shareVo.setUsername(user.getUsername());
            shareVo.setViewNum(share.getViewNum());

            shareListVo.add(shareVo);
        }
        return shareListVo;
    }


}
