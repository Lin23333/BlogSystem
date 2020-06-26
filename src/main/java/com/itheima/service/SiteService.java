package com.itheima.service;

import com.itheima.model.dto.ArticleDTO;
import com.itheima.model.entity.Article;
import com.itheima.model.entity.Comment;
import com.itheima.model.responseData.StaticticsBo;

import java.util.List;

/**
 *  博客站点统计服务
 */
public interface SiteService {

    // 最新收到的评论
    List<Comment> recentComments(int count);

    // 最新发表的文章
    List<ArticleDTO> recentArticles(int count);

    // 获取后台统计数据
    StaticticsBo getStatistics();

    // 更新某个文章的统计数据
    void updateStatistics(int articleId);

}
