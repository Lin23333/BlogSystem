package com.itheima.service.impl;

import com.github.pagehelper.PageInfo;
import com.itheima.model.entity.Comment;
import com.itheima.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Date;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    void getComments() {
        PageInfo<Comment> comments = commentService.getComments(1, 1, 5);
        comments.getList().forEach(System.out::println);
    }

    @Test
    void pushComment() {
        Comment comment = new Comment();
        comment.setArticleId(13);
        comment.setCreated(new Date());
        comment.setContent("hhhhh ");
        comment.setStatus("approved");
        comment.setAuthor("琳333");
        int result = commentService.pushComment(comment);
        System.out.println(result);
    }
}