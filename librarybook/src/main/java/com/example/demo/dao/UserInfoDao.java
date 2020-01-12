package com.example.demo.dao;

import com.example.demo.model.UserInfo;

import java.util.HashMap;
import java.util.List;

public interface UserInfoDao {
    void add(UserInfo userInfo);
    void update(String userName, HashMap<String,Object> map);
    UserInfo get(String userName);
    List<UserInfo> getByRegistYear(String year);
    List<UserInfo> getAll();
}
