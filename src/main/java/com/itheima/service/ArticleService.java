package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.model.dto.ArticleDTO;

import java.util.List;

/**
 *  文章操作业务层
 */
public interface ArticleService {

    // 分页查询文章列表
    PageInfo<ArticleDTO> selectArticleWithPage(Integer page, Integer count);

    // 统计热度排名前十的文章信息
    List<ArticleDTO> getHeatArticles();

}
