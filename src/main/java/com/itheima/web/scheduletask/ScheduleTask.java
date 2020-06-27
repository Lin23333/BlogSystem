package com.itheima.web.scheduletask;

import com.itheima.mapper.StatisticMapper;
import com.itheima.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *  定时任务
 */
@Component
public class ScheduleTask {
    @Autowired
    StatisticMapper statisticMapper;
    @Autowired
    private MailUtils mailUtils;

    @Value("${spring.mail.username}")
    private String mailto;

    // 定时邮件发送任务，每月1日中午12点整发送邮件
    @Scheduled(cron = "0 0 12 1 * ?")
//    @Scheduled(cron = "0 */1 * * * ?")
    public void sendEmail() {
        // 定制邮件内容
        long totalVisit = statisticMapper.getTotalVisit();
        long totalComment = statisticMapper.getTotalComment();
        StringBuilder content = new StringBuilder();
        content.append("博客系统总访问量为：").append(totalVisit).append("人次\n")
                .append("博客系统总评论量为：").append(totalComment).append("人次\n");
        mailUtils.sendSimpleEmail(mailto, "个人博客系统流量统计情况", content.toString());
    }
}
