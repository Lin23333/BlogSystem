package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.model.dto.ArticleDTO;
import com.itheima.model.entity.Article;

import java.util.List;

/**
 *  文章操作业务层
 */
public interface ArticleService {

    // 分页查询文章列表
    PageInfo<ArticleDTO> selectArticleWithPage(Integer page, Integer count);

    // 统计热度排名前十的文章信息
    List<ArticleDTO> getHeatArticles();

    // 根据ID查询文章
    Article selectArticleWithId(Integer id);

    // 新增文章
    void publish(Article article);

    // 更新文章
    void updateArticleWithId(Article article);

    // 根据主键删除文章
    void deleteArticleWithId(int id);
}
