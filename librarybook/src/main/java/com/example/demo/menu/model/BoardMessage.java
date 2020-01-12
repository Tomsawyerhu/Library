package com.example.demo.menu.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "boardMessage_")
@Proxy(lazy = false)  //注意懒加载
public class BoardMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;            //id

    @Column(name="name")
    private String name;

    @Column(name="message")
    private String message;

    public BoardMessage(String name,String message){this.name=name;this.message=message;}
    public BoardMessage(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setMessage(String message){
        this.message=message;
    }


    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public String getMessage(){
        return message;
    }

}
