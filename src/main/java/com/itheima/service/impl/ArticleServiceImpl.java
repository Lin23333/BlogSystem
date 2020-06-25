package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mapper.ArticleMapper;
import com.itheima.mapper.StatisticMapper;
import com.itheima.model.dto.ArticleDTO;
import com.itheima.model.entity.Article;
import com.itheima.model.entity.Statistic;
import com.itheima.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
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

        Example example = new Example(Article.class);
        example.setOrderByClause("id DESC");
        List<Article> articleList = articleMapper.selectByExample(example);

        List<ArticleDTO> articleDTOList = new ArrayList<>();
        articleList.forEach(article -> {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setArticle(article);
            articleDTO.setStatistic(statisticMapper.selectStatisticWithArticleId(article.getId()));
            articleDTOList.add(articleDTO);
        });
        PageInfo<ArticleDTO> pageInfo = new PageInfo<>(articleDTOList);
        return pageInfo;
    }

    @Override
    public List<ArticleDTO> getHeatArticles() {
        List<ArticleDTO> list = statisticMapper.getStatistic();
        return list;
    }
}