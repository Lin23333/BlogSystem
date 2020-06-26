package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ArticleMapper;
import com.itheima.mapper.CommentMapper;
import com.itheima.mapper.StatisticMapper;
import com.itheima.model.dto.ArticleDTO;
import com.itheima.model.entity.Article;
import com.itheima.model.entity.Comment;
import com.itheima.model.entity.Statistic;
import com.itheima.model.responseData.StaticticsBo;
import com.itheima.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SiteServiceImpl implements SiteService {

    @Autowired private CommentMapper commentMapper;

    @Autowired private ArticleMapper articleMappere;

    @Autowired private StatisticMapper statisticMapper;

    @Override
    public List<Comment> recentComments(int limit) {
        PageHelper.startPage(1, limit > 10 || limit < 1 ? 10 : limit);
        List<Comment> byPage = commentMapper.selectNewComment();
        return byPage;
    }

    @Override
    public List<ArticleDTO> recentArticles(int limit) {
        PageHelper.startPage(1, limit > 10 || limit < 1 ? 10 : limit);
        List<ArticleDTO> list = articleMappere.selectArticleWithPage();
        list.forEach(articleDTO ->
            articleDTO.setStatistic(statisticMapper.selectStatisticWithArticleId(articleDTO.getId()))
        );
        return list;
    }

    @Override
    public StaticticsBo getStatistics() {
        StaticticsBo staticticsBo = new StaticticsBo();
        Integer articles = articleMappere.selectCount(null);
        Integer comments = commentMapper.selectCount(null);
        staticticsBo.setArticles(articles);
        staticticsBo.setComments(comments);
        return staticticsBo;
    }

    /**
     *  文章的点击量 + 1
     * @param articleId - 文章ID
     */
    @Override
    public void updateStatistics(int articleId) {
        Statistic statistic = statisticMapper.selectStatisticWithArticleId(articleId);
        statistic.setHits(statistic.getHits() + 1);
        statistic.setCommentsNum(null);
        statisticMapper.updateArticleHitsOrCommentsNumWithId(statistic);
    }
}
