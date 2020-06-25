package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.model.dto.ArticleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceImplTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void selectArticleWithPage() {

        PageInfo<ArticleDTO> pageInfo = articleService.selectArticleWithPage(0, 10);
        List<ArticleDTO> list = pageInfo.getList();
        list.forEach(System.out::println);
    }

    @Test
    void getHeatArticles() {
        articleService.getHeatArticles().forEach(articleDTO -> {
            System.out.println(articleDTO.getArticle().getId());
        });
    }
}