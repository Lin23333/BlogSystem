package com.itheima.mapper;

import com.itheima.model.dto.ArticleDTO;
import com.itheima.model.entity.Article;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ArticleMapper extends Mapper<Article> {

    // 文章分页查询
    List<ArticleDTO> selectArticleWithPage();

    // 发表文章
    Integer insertArticle(Article article);

    // 通过ID更新文章
    Integer updateArticleWithId(Article article);

}
