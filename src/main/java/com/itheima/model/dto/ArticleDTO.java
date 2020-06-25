package com.itheima.model.dto;

import com.itheima.model.entity.Article;
import com.itheima.model.entity.Statistic;
import lombok.Data;

@Data
public class ArticleDTO {

    private Article article;

    private Statistic statistic;

}
