package com.itheima.mapper;

import com.itheima.model.dto.ArticleDTO;
import com.itheima.model.entity.Statistic;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface StatisticMapper extends Mapper<Statistic> {

    Statistic selectStatisticWithArticleId(Integer articleId);

    List<ArticleDTO> getStatistic();

}
