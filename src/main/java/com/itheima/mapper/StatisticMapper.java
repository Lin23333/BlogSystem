package com.itheima.mapper;

import com.itheima.model.dto.ArticleDTO;
import com.itheima.model.entity.Statistic;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface StatisticMapper extends Mapper<Statistic> {

    // 根据文章ID查询点击量和评论量相关信息
    Statistic selectStatisticWithArticleId(Integer articleId);

    // 查询热度排行前十的文章信息
    List<ArticleDTO> getStatistic();

    // 新增文章对应的统计信息
    void insertStatistic(Statistic statistic);

    // 通过文章ID更新点击量或评论量
    void updateArticleHitsOrCommentsNumWithId(Statistic statistic);

    // 根据文章ID删除统计数据
    void deleteStatisticWithId(int aid);

}
