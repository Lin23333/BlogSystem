package com.itheima.mapper;

import com.itheima.model.entity.Comment;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CommentMapper extends Mapper<Comment> {

    // 分页展示某个文章的评论
    List<Comment> selectCommentWithPage(Integer aid);

    // 后台查询最新几条评论
    List<Comment> selectNewComment();

    // 发表评论
    Integer insertComment(Comment comment);

    // 根据文章ID删除评论
    void deleteCommentWithId(int aId);
}
