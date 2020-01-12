package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "visitInfo")
public class VisitInfo {

    @Field
    private String userName;
    @Field
    private String visitTime;

    @Override
    public String toString() {
        return "VisitInfo{" +
                "userName='" + userName + '\'' +
                ", visitTime='" + visitTime + '\'' +
                '}';
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }
}
