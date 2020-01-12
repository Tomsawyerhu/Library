package com.example.demo.dao;

import com.example.demo.model.Article;

import java.util.List;

public interface ArticleDao {
    void add(Article article);
    void delete(String head,String author);
    Article randomGet(); //随机访问一篇文章
    List<Article> getAll();
}
