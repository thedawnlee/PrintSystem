package com.dawn.dao;

import com.dawn.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> queryCommentFirstLevel(@Param("targetId")Integer targetId);

    List<Comment> queryCommentByParentId(@Param("targetId")Integer targetId, @Param("parentId")Integer parentId);



}