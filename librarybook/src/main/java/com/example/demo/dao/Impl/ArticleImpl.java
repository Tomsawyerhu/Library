package com.example.demo.dao.Impl;


import com.example.demo.dao.ArticleDao;
import com.example.demo.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;


@Component
public class ArticleImpl implements ArticleDao {
    @Autowired
    MongoTemplate template;


    @Override
    public void add(Article article) {
        template.insert(article);
    }

    @Override
    public void delete(String head, String author) {
        Criteria criteria=new Criteria();
        Query query=new Query(criteria.and("head").is(head).and("author").is(author));
        template.remove(query,Article.class);
    }

    @Override
    public Article randomGet() {
        List<Article> list=template.findAll(Article.class);
        Random random=new Random();
        int randomNum=random.nextInt(list.size());

        return list.get(randomNum);
    }

    @Override
    public List<Article> getAll() {
        return template.findAll(Article.class);
    }
}
