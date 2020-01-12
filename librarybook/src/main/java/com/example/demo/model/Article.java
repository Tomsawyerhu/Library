package com.example.demo.model;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "article")
public class Article {
    @Field
    private String head;
    @Field
    private String content;
    @Field
    private String author;
    @Field
    private String time;

    public Article(String head, String content, String author, String time) {
        this.head = head;
        this.content = content;
        this.author = author;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Article{" +
                "head='" + head + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
