package com.itheima.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  文章详情实体类
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Data
@Table(name = "t_article")
public class Article {

  @Id
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
  private Integer allowComment;         // 是否允许评论（默认1）
  private String thumbnail;             // 文章缩略图


}
