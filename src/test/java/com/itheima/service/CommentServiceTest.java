package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.model.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    void getComments() {
        PageInfo<Comment> comments = commentService.getComments(1, 0, 10);
        comments.getList().forEach(System.out::println);
    }
}