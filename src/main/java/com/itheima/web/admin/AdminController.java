package com.itheima.web.admin;

import com.github.pagehelper.PageInfo;
import com.itheima.model.dto.ArticleDTO;
import com.itheima.model.entity.Article;
import com.itheima.model.entity.Comment;
import com.itheima.model.responseData.ArticleResponseData;
import com.itheima.model.responseData.StaticticsBo;
import com.itheima.service.ArticleService;
import com.itheima.service.SiteService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  后台管理模块
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private SiteService siteService;

    @Autowired
    private ArticleService articleService;

    @GetMapping({"", "/index"})
    public String index(Model model) {
        // 获取最新的5篇博客、评论以及统计数据
        List<ArticleDTO> articleDTOS = siteService.recentArticles(5);
        List<Comment> comments = siteService.recentComments(5);
        StaticticsBo staticticsBo = siteService.getStatistics();
        model.addAttribute("comments", comments);
        model.addAttribute("articles", articleDTOS);
        model.addAttribute("statistics", staticticsBo);
        return "back/index";
    }

    /**
     *  跳转到新增文章页面
     * @return
     */
    @GetMapping("/article/toEditPage")
    public String newArticle() {
        return "back/article_edit";
    }

    /**
     *  发表文章
     * @param article
     * @return
     */
    @PostMapping("/article/publish")
    public @ResponseBody ArticleResponseData publishArticle(Article article) {
        if(StringUtils.isBlank(article.getCategories()))
            article.setCategories("默认分类");
        try{
            articleService.publish(article);
            LOGGER.info("文章发布成功");
            return ArticleResponseData.ok();
        } catch (Exception e) {
            LOGGER.error("文章发布失败，错误信息：{}", e.getMessage());
            return ArticleResponseData.fail();
        }
    }

    @GetMapping("/article")
    public String index(@RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "10") int count, Model model) {
        PageInfo<ArticleDTO> pageInfo = articleService.selectArticleWithPage(page, count);
        model.addAttribute("articles", pageInfo);
        return "back/article_list";
    }

    @GetMapping("/article/{id}")
    public String editArticle(@PathVariable Integer id, Model model) {
        Article article = articleService.selectArticleWithId(id);
        model.addAttribute("contents", article);
        model.addAttribute("categories", article.getCategories());
        return "back/article_edit";
    }

    @PostMapping("/article/modify")
    public @ResponseBody ArticleResponseData modifyArticle(Article article) {
        try {
            articleService.updateArticleWithId(article);
            LOGGER.info("文章更新成功");
            return ArticleResponseData.ok();
        } catch (Exception e) {
            LOGGER.error("文章更新失败，错误信息：{}", e.getMessage());
            return ArticleResponseData.fail();
        }
    }

    @PostMapping("/article/delete")
    public @ResponseBody ArticleResponseData delete(int id) {
        try {
            articleService.deleteArticleWithId(id);
            LOGGER.info("文章删除成功");
            return ArticleResponseData.ok();
        } catch (Exception e) {
            LOGGER.error("文章删除失败，错误信息：{}", e.getMessage());
            return ArticleResponseData.fail();
        }
    }

}
