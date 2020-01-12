package com.example.demo.user.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="userSession_")
@Proxy(lazy = false)  //注意懒加载
public class UserSession {
    public UserSession(int id,String sessionId,String username,String expire){
        this.id=id;
        this.sessionId=sessionId;
        this.username=username;
        this.expire=expire;
    }
    public UserSession(String sessionId,String username){
        this.sessionId=sessionId;
        this.username=username;
    }
    public UserSession(String sessionId,String username,String expire){
        this.sessionId=sessionId;
        this.username=username;
        this.expire=expire;
    }
    public UserSession(String username){
        this.username=username;
    }
    public UserSession(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="sessionId")
    private String sessionId;

    @Column(name="username")
    private String username;

    @Column(name="expire")
    private String expire;

    public void setId(int id){this.id=id;}
    public void setSessionId(String sessionId){this.sessionId=sessionId;}
    public void setUsername(String username){this.username=username;}
    public void setExpire(String expire){this.expire=expire;}
    public int getId(){return  id;}
    public String getSessionId(){return sessionId;}
    public String getUsername(){return username;}
    public String getExpire(){return expire;}

    public boolean isExpire(){ //判断token是否过期
        if(Long.parseLong(this.expire)> new Date().getTime()) return false;
        else return true;
    }
}
