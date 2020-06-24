package com.itheima.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Table;
import java.util.Date;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Data
@Table(name = "t_article")
public class Article {

  private Integer id;
  private String title;
  private String content;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date created;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date modified;
  private String categories;
  private String tags;
  private Integer allowComment;
  private String thumbnail;


}
