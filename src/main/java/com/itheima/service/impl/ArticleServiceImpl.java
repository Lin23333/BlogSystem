package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mapper.ArticleMapper;
import com.itheima.mapper.CommentMapper;
import com.itheima.mapper.StatisticMapper;
import com.itheima.model.dto.ArticleDTO;
import com.itheima.model.entity.Article;
import com.itheima.model.entity.Statistic;
import com.itheima.service.ArticleService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private StatisticMapper statisticMapper;
    @Autowired
    private CommentMapper commentMapper;

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
    @Cacheable(cacheNames = "article", key = "'id::'+#id")
    public Article selectArticleWithId(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void publish(Article article) {
        article.setContent(EmojiParser.parseToAliases(article.getContent()));
        article.setCreated(new Date());
        articleMapper.insertArticle(article);
        statisticMapper.insertStatistic(article.getId());
    }

    @Override
    @CacheEvict(cacheNames = "article", key = "'id::'+#id")
    public void updateArticleWithId(Article article) {
        article.setModified(new Date());
        articleMapper.updateArticleWithId(article);
    }

    @Override
    @CacheEvict(cacheNames = "article", key = "'id::'+#id")
    public void deleteArticleWithId(int id) {
        articleMapper.deleteByPrimaryKey(id);
        statisticMapper.deleteStatisticWithId(id);
        commentMapper.deleteCommentWithId(id);
    }

}
