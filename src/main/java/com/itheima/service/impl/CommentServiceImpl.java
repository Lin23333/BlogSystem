package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mapper.ArticleMapper;
import com.itheima.mapper.CommentMapper;
import com.itheima.mapper.StatisticMapper;
import com.itheima.model.entity.Comment;
import com.itheima.model.entity.Statistic;
import com.itheima.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private StatisticMapper statisticMapper;

    @Override
    public PageInfo<Comment> getComments(Integer aid, int page, int count) {
        PageHelper.startPage(page, count);
        List<Comment> commentList = commentMapper.selectCommentWithPage(aid);
        PageInfo<Comment> commentInfo = new PageInfo<>(commentList);
        return commentInfo;
    }

    @Override
    public int pushComment(Comment comment) {
        Integer result = commentMapper.insertComment(comment);
        Statistic statistic = statisticMapper.selectStatisticWithArticleId(comment.getArticleId());
        statistic.setHits(null);
        statistic.setCommentsNum(statistic.getCommentsNum() + 1);
        statisticMapper.updateArticleHitsOrCommentsNumWithId(statistic);
        return result;
    }

}
