package com.itheima.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Table;
import java.util.Date;

/**
 *  文章评论实体类
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Data
@Table(name = "t_comment")
public class Comment {

  private Integer id;                   // 评论ID
  private Integer articleId;            // 评论关联的文章ID
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date created;                // 创建时间
  private String ip;                   // 评论用户所在IP地址
  private String content;              // 评论内容
  private String status;               // 评论状态（默认approved）
  private String author;               // 评论作者名

}
