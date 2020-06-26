package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mapper.ArticleMapper;
import com.itheima.mapper.StatisticMapper;
import com.itheima.model.dto.ArticleDTO;
import com.itheima.model.entity.Article;
import com.itheima.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private StatisticMapper statisticMapper;

    @Override
    public PageInfo<ArticleDTO> selectArticleWithPage(Integer page, Integer count) {
        PageHelper.startPage(page, count);
        List<ArticleDTO> articleList = articleMapper.selectArticleWithPage();

        articleList.forEach(articleDTO ->
            articleDTO.setStatistic(statisticMapper.selectStatisticWithArticleId(articleDTO.getId()))
        );
        PageInfo<ArticleDTO> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }

    @Override
    public List<ArticleDTO> getHeatArticles() {
        List<ArticleDTO> list = statisticMapper.getStatistic();
        return list;
    }

    @Override
    public Article selectArticleWithId(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

}
