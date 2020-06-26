package com.itheima.service.impl;

import com.itheima.model.dto.ArticleDTO;
import com.itheima.model.entity.Article;
import com.itheima.model.entity.Comment;
import com.itheima.model.responseData.StaticticsBo;
import com.itheima.service.SiteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SiteServiceTest {

    @Autowired
    private SiteService siteService;

    @Test
    void recentComments() {
        List<Comment> comments = siteService.recentComments(6);
        comments.forEach(System.out::println);
    }

    @Test
    void recentArticles() {
        List<ArticleDTO> list = siteService.recentArticles(7);
        list.forEach(System.out::println);
    }

    @Test
    void getStatistics() {
        StaticticsBo staticticsBo = siteService.getStatistics();
        System.out.println(staticticsBo);
    }

    @Test
    void updateStatistics() {
        siteService.updateStatistics(13);
    }
}