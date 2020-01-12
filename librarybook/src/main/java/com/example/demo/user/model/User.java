package com.example.demo.user.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name="user_")
@Proxy(lazy = false)  //注意懒加载

public class User {
    public User(int id,String username,String password,String phone){
        this.id=id;
        this.username=username;
        this.password=password;
        this.phone=phone;
    }
    public User(String username,String password,String phone){
        this.username=username;
        this.password=password;
        this.phone=phone;
    }
    public User(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="phone")
    private String phone;

    public void setId(int id){this.id=id;}

    public void setUsername(String username){
        this.username=username;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setPhone(String phone){
        this.phone=phone;
    }

    public int getId(){return  id;}

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getPhone(){
        return phone;
    }

    public String toString(){
        return "用户名:"+username+"密码:"+password+"手机:"+phone;
    }
}
