package com.itheima.model.entity;

import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Table;

/**
 *  文章统计实体类
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Data
@Table(name = "t_statistic")
public class Statistic {

  private Integer id;                        // 文章统计ID
  private Integer articleId;                 // 文章ID
  private Integer hits;                      // 文章点击量
  private Integer commentsNum;               // 文章评论量

}
