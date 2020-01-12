package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "userInfo")
public class UserInfo {
    @Field
    private String userName;
    @Field
    private String registTime;
    @Field
    private String cellPhoneNumber;
    @Field
    private OtherInfo otherInfo;

    public UserInfo(String userName, String registTime, String cellPhoneNumber, OtherInfo otherInfo) {
        this.userName = userName;
        this.registTime = registTime;
        this.cellPhoneNumber = cellPhoneNumber;
        this.otherInfo = otherInfo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRegistTime() {
        return registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public OtherInfo getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(OtherInfo otherInfo) {
        this.otherInfo = otherInfo;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", registTime='" + registTime + '\'' +
                ", cellPhoneNumber='" + cellPhoneNumber + '\'' +
                otherInfo.toString()+
                '}';
    }
}
