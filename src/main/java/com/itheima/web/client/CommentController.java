package com.itheima.web.client;

import com.itheima.model.entity.Comment;
import com.itheima.model.responseData.ArticleResponseData;
import com.itheima.service.CommentService;
import com.itheima.utils.MyUtils;
import com.itheima.utils.NetworkUtil;
import com.vdurmont.emoji.EmojiParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 *  评论控制层
 */
@RestController
@RequestMapping("/comments")
public class CommentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    /**
     *  发布文章id的评论
     * @param request
     * @param aid - 文章ID
     * @param text - 评论内容
     * @return
     */
    @PostMapping("/publish")
    public ArticleResponseData publishComment(HttpServletRequest request, Integer aid, String text) {
        // 去除JS脚本
        text = MyUtils.cleanXSS(text);
        text = EmojiParser.parseToAliases(text);
        // 获取当前登录用户
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = new Comment();
        comment.setArticleId(aid);
        comment.setIp(NetworkUtil.getIpAddress(request));
        comment.setCreated(new Date());
        comment.setAuthor(user.getUsername());
        comment.setContent(text);
        try{
            commentService.pushComment(comment);
            LOGGER.info("发布评论成功，对应文章ID：{}", aid);
            return ArticleResponseData.ok();
        } catch (Exception e) {
            LOGGER.info("发布评论失败，对应文章ID：{}；错误描述：{}", aid, e.getMessage());
            return ArticleResponseData.fail();
        }
    }


}
