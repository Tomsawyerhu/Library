package com.example.demo.model;

import java.util.List;

public class BookInfo {
    private String name;
    private String author;
    private String publisher;
    private String time;
    private String content;
    public BookInfo(String... a){
        if(a.length==5){
        name=a[0];
        author=a[1];
        publisher=a[2];
        time=a[3];
        content=a[4];
        }
    }
    public BookInfo(List<String> a){
        name=a.get(0);
        author=a.get(1);
        publisher=a.get(2);
        time=a.get(3);
        content=a.get(4);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setTime(String time){
        this.time=time;
    }

    public void setContent(String content){
        this.content=content;
    }

    public String getName(){
        return name;
    }

    public String getAuthor(){
        return author;
    }

    public String getPublisher(){
        return publisher;
    }

    public String getTime(){
        return time;
    }

    public String getContent(){
        return content;
    }
}
