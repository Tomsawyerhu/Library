package com.example.demo.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name="book_")
@Proxy(lazy = false)  //注意懒加载
public class Book {
    Book(int id,String file_path1,String file_path2,String file_path3,String category,String name,String indexing,String intro1,String intro2){
        this.id=id;
        this.name=name;
        this.category=category;
        this.file_path1=file_path1;
        this.file_path2=file_path2;
        this.file_path3=file_path3;
        this.indexing=indexing;
        this.intro1=intro1;
        this.intro2=intro2;
    }
    Book(){}
    Book(int id,String category,String name,String indexing,String intro1,String intro2){
        this.id=id;
        this.name=name;
        this.category=category;
        this.indexing=indexing;
        this.intro1=intro1;
        this.intro2=intro2;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", file_path1='" + file_path1 + '\'' +
                ", file_path2='" + file_path2 + '\'' +
                ", file_path3='" + file_path3 + '\'' +
                ", indexing='" + indexing + '\'' +
                ", intro1='" + intro1 + '\'' +
                ", intro2='" + intro2 + '\'' +
                ", stamp=" + stamp +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;            //id

    @Column(name="name")
    private String name;       //书名
    @Column(name="category")
    private String category;   //分类



    @Column(name="file_path1")
    private String file_path1;  //图书文件的路径
    @Column(name="file_path2")
    private String file_path2;  //图书简介的路径
    @Column(name="file_path3")
    private String file_path3;  //图书图片的路径
    @Column(name = "indexing")
    private String indexing;

    @Column(name="intro1")
    private String intro1;       //描述1

    @Column(name="intro2")
    private String intro2;       //描述2

    @Column(name="stamp")        //收藏量
    private int stamp=0;


    public String getFilePath1(){
        return file_path1;
    }
    public String getFilePath2(){
        return file_path2;
    }
    public String getFilePath3(){
        return file_path3;
    }
    public int getId(){
        return id;
    }
    public String getCategory(){
        return category;
    }
    public String getName(){
        return name;
    }

    public String getIndexing() {
        return indexing;
    }

    public String getIntro1() {
        return intro1;
    }

    public String getIntro2() {
        return intro2;
    }

    public void setFilePath1(String file_path1){
        this.file_path1=file_path1;
    }
    public void setFilePath2(String file_path2){
        this.file_path2=file_path2;
    }
    public void setFilePath3(String file_path3){
        this.file_path3=file_path3;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setCategory(String category){
        this.category=category;
    }
    public void setName(String name){
        this.name=name;
    }

    public void setIndexing(String indexing) {
        this.indexing = indexing;
    }

    public void setIntro1(String intro1) {
        this.intro1 = intro1;
    }

    public void setIntro2(String intro2) {
        this.intro2 = intro2;
    }

    public int getStamp() {
        return stamp;
    }

    public void setStamp(int stamp) {
        this.stamp = stamp;
    }
}
