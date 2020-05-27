package com.dawn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.dawn.commons.ServerResponse;
import com.dawn.config.ParameterConfig;
import com.dawn.dao.CommentMapper;
import com.dawn.dao.ShareMapper;
import com.dawn.dao.UserMapper;
import com.dawn.enums.CommentStatusEnum;
import com.dawn.enums.CommentTypeEnum;
import com.dawn.form.CommentForm;
import com.dawn.pojo.Comment;
import com.dawn.pojo.Share;
import com.dawn.pojo.User;
import com.dawn.service.ICommentService;
import com.dawn.vo.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service(value = "iCommentService")
public class CommentServiceImpl implements ICommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShareMapper shareMapper;

    @Autowired
    private ParameterConfig parameterConfig;


    public PageInfo<CommentVo> queryCommentFirstLevel(Integer targetId, Integer pageNum, Integer pageSize){

        PageHelper.startPage(pageNum, pageSize);

        List<CommentVo> commentVoList = new ArrayList<>();

        List<Comment> comments = commentMapper.queryCommentFirstLevel(targetId);

        for (Comment comment : comments){
            CommentVo commentVo = new CommentVo();
            BeanUtils.copyProperties(comment, commentVo);

            User user = userMapper.selectByPrimaryKey(comment.getUserId());
            if ( user != null){
                commentVo.setHeaderPic(  parameterConfig.getImageHost() + user.getHeaderPic());
                commentVo.setUserName(user.getUsername());
            }

            commentVoList.add(commentVo);
        }

        recursionChildren(commentVoList);

        PageInfo<CommentVo> pageInfo = new PageInfo<>(commentVoList);

        return pageInfo;
    }

    private void recursionChildren (List<CommentVo> commentVoList) {

        // 递归生成二级评论, 遍历每一个评论,看看此评论有没有下级
        for (CommentVo commentVoItem : commentVoList){

            Integer targetId = commentVoItem.getTargetId();
            Integer parentId = commentVoItem.getId();

            // 获取当前评论 id 为 parentId 的博客列表.
            List<Comment> comments = commentMapper.queryCommentByParentId(targetId, parentId);

            // 递归结束标志
            if ( !CollectionUtils.isEmpty(comments) ){

                List<CommentVo> childrenList = new ArrayList<>();

                for (Comment comment : comments){

                    CommentVo commentVo = new CommentVo();
                    BeanUtils.copyProperties(comment, commentVo);

                    Integer commentUserId = comment.getUserId();
                    User user = userMapper.selectByPrimaryKey(commentUserId);
                    if ( user != null){
                        commentVo.setHeaderPic( parameterConfig.getImageHost() + user.getHeaderPic());
                        commentVo.setUserName(user.getUsername());
                    }
                    childrenList.add(commentVo);
                }

                commentVoItem.setChildren(childrenList);

                //递归
                recursionChildren(childrenList);

            }else {
                commentVoItem.setChildren(new ArrayList<CommentVo>());
            }
        }
    }

    /**
     * 创建评论
     * @param commentForm
     * @param userId
     * @return
     */
    public ServerResponse createComment(CommentForm commentForm, Integer userId){


        Integer targetId = commentForm.getTargetId();

        String  content = commentForm.getContent();

        Integer parentId = commentForm.getParentId();

        Share share = shareMapper.selectByPrimaryKey(targetId);

        if ( share == null ){
            return ServerResponse.createByErrorMessage("当前分享不存在");
        }

        Comment comment = new Comment();

        comment.setCommentType(CommentTypeEnum.SHARE.getCode());
        comment.setContent(content);
        comment.setUserId(userId);
        comment.setIp("");
        comment.setParentId(parentId);
        comment.setStatus(CommentStatusEnum.NOT_DELETE.getCode());
        comment.setTargetId(targetId);

        int insert = commentMapper.insert(comment);

        if ( insert <= 0 ){
            return ServerResponse.createByErrorMessage("评论失败");
        }

        return ServerResponse.createBySuccess("评论成功");
    }

}
