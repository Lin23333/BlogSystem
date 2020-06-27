package com.itheima.mapper;

import com.itheima.model.dto.ArticleDTO;
import com.itheima.model.entity.Article;
import com.itheima.model.entity.Statistic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
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

    public void selectArticleWithPage() {

    }

    @Test
    public void insertArticle() {
        Article a = new Article();
        a.setTitle("林圳我爱你");
        a.setContent("111111");
        a.setCreated(new Date());
        a.setAllowComment(true);

        Integer result = articleMapper.insertArticle(a);
        System.out.println(result);
    }

    @Test
    public void updateArticleWithId() {

        Article a = new Article();
        a.setId(13);
        a.setTitle("林圳");
//        a.setContent("123333311111");
//        a.setModified(new Date());
//        a.setAllowComment(1);
//        Statistic s = new Statistic();
//        s.setHits(1);
//        s.setCommentsNum(57);
//        a.setStatistic(s);
        Integer result = articleMapper.updateArticleWithId(a);
        System.out.println(result);
    }
}