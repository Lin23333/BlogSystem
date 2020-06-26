package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.model.entity.Comment;

/**
 *  文章评论业务处理接口
 */
public interface CommentService {

    // 获取文章下的评论
    PageInfo<Comment> getComments(Integer aid, int page, int count);

    // 用户发表评论
    int pushComment(Comment comment);

}
