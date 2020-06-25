package com.itheima.web.client;

import com.github.pagehelper.PageInfo;
import com.itheima.model.dto.ArticleDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@SpringBootTest
class IndexControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void index() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        int status = mvcResult.getResponse().getStatus();
        PageInfo<ArticleDTO> articles = (PageInfo<ArticleDTO>) mvcResult.getRequest().getAttribute("articles");
        List<ArticleDTO> articleList = (List<ArticleDTO>) mvcResult.getRequest().getAttribute("articleList");

        System.out.println("status: " + status);
        articles.getList().forEach(System.out::println);
        System.out.println("#######");
        articleList.forEach(System.out::println);
//        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void index1() {
    }
}