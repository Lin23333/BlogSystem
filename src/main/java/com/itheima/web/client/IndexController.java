package com.itheima.web.client;

import com.github.pagehelper.PageInfo;
import com.itheima.model.dto.ArticleDTO;
import com.itheima.model.entity.Article;
import com.itheima.model.entity.Comment;
import com.itheima.service.ArticleService;
import com.itheima.service.CommentService;
import com.itheima.service.SiteService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired private ArticleService articleService;
    @Autowired private CommentService commentService;
    @Autowired private SiteService siteService;

    /**
     *  首页
     * @param model
     * @return
     */
    @GetMapping({"/", "index", "index.html"})
    public String index(Model model) {
        return this.index(model, 1, 5);
    }

    /**
     *  首页文章路由
     * @param model
     * @param page
     * @param count
     * @return
     */
    @GetMapping("/page/{p}")
    public String index(Model model, @PathVariable("p") int page,
                        @RequestParam(value = "count", defaultValue = "5") int count) {
        PageInfo<ArticleDTO> articles = articleService.selectArticleWithPage(page, count);

        // 获取文章热度统计信息
        List<ArticleDTO> articleDTOList = articleService.getHeatArticles();
        model.addAttribute("articles", articles);
        model.addAttribute("articleList", articleDTOList);
        LOGGER.info("分页获取文章信息：页码{}，条数{}", page, count);

        return "client/index";
    }

    /**
     *  文章详情查询
     * @param id - 文章ID
     * @param model
     * @return
     */
    @GetMapping("/article/{id}")
    public String getArticleById(@PathVariable Integer id, String cp, Model model) {
        Article article = articleService.selectArticleWithId(id);
        if(article != null) {
            // 获取该文章的评论
            if(article.getAllowComment()) {  // 该文章允许评论
//                String cp = (String) model.getAttribute("cp");
                cp = StringUtils.isBlank(cp) ? "1" : cp;
                PageInfo<Comment> comments = commentService.getComments(id, Integer.parseInt(cp), 3);
                model.addAttribute("cp", cp);
                model.addAttribute("comments", comments);
            }
            // 更新文章内点击量
            siteService.updateStatistics(id);
            model.addAttribute("article", article);
            return "client/articleDetails";
        } else {
            LOGGER.warn("查询文章详情结果为空，查询文章ID：{}", id);
            return "comm/error_404";
        }
    }


}
