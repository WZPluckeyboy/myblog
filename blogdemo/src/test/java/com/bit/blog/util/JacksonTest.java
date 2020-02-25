package com.bit.blog.util;

import com.bit.blog.entity.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JacksonTest {

    @Test
    public void testJackson(){
        List<Article> articles = new ArrayList<>();
        Article article1 = new Article();
        article1.setId(1);
        article1.setTitle("我的博客1");
        article1.setContent("内容1");
        article1.setCreateTime(new Date());
        articles.add(article1);
        Article article2 = new Article();
        article2.setId(2);
        article2.setTitle("我的博客2");
        article2.setContent("内容2");
        article2.setCreateTime(new Date());
        articles.add(article2);
        ObjectMapper  objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            String result = objectMapper.writeValueAsString(articles);
            System.out.println(result);
//            Assert.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
