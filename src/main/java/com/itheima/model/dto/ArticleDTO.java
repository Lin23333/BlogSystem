package com.itheima.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itheima.model.entity.Article;
import com.itheima.model.entity.Statistic;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ArticleDTO {

    private Integer id;                    // 文章ID
    private String title;                  // 文章标题
    private String content;                // 文章内容
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;                 // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modified;                // 修改时间
    private String categories;            // 文章分类
    private String tags;                  // 文章标签
    private Boolean allowComment;         // 是否允许评论（默认1）
    private String thumbnail;             // 文章缩略图

    private Statistic statistic;          // 文章统计信息

}
