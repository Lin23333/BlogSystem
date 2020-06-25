package com.itheima.web.client;

import com.github.pagehelper.PageInfo;
import com.itheima.model.dto.ArticleDTO;
import com.itheima.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ArticleService articleService;

    @GetMapping({"/", "index", "index.html"})
    public String index(Model model) {
        return this.index(model, 1, 5);
    }

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

}
