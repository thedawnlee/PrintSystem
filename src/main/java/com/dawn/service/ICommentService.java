package com.dawn.service;

import com.github.pagehelper.PageInfo;
import com.dawn.commons.ServerResponse;
import com.dawn.form.CommentForm;
import com.dawn.vo.CommentVo;


public interface ICommentService {

    PageInfo<CommentVo> queryCommentFirstLevel(Integer targetId, Integer pageNum, Integer pageSize);

    ServerResponse createComment(CommentForm commentForm, Integer userId);

}
