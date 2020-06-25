package com.itheima.mapper;

import com.itheima.model.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ArticleMapperTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void testFindId() {
        List<Article> list = articleMapper.selectAll();
        list.forEach(System.out::println);
    }

}