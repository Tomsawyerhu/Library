package com.example.demo.index.model;

public class Token {
    private String startTime,sessionId,username;
    Token(String startTime,String username,String sessionId){
        this.sessionId=sessionId;
        this.startTime=startTime;
        this.username=username;
    }
    public String getStartTime(){return startTime;}
    public String getSessionId(){return sessionId;}
    public String getUsername(){return username;}
}
