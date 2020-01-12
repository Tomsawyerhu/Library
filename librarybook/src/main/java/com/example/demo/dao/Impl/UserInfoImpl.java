package com.example.demo.dao.Impl;

import com.example.demo.dao.UserInfoDao;
import com.example.demo.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;


@Component
public class UserInfoImpl implements UserInfoDao {
    @Autowired
    MongoTemplate template;


    @Override
    public void add(UserInfo userInfo) {
        template.insert(userInfo);
    }

    @Override
    public void update(String userName, HashMap<String,Object> map) {
        Query query=new Query(Criteria.where("userName").is(userName));
        Update update=new Update();
        for(String key:map.keySet()){
            update=update.set(key,map.get(key));
        }
        template.updateFirst(query, update, UserInfo.class);
    }

    @Override
    public UserInfo get(String userName) {
        Query query=new Query(Criteria.where("userName").is(userName));
        return template.find(query,UserInfo.class).get(0);
    }

    @Override
    public List<UserInfo> getByRegistYear(String year) {
        Query query=new Query(Criteria.where("registTime").regex(year+"-*"));
        return template.find(query,UserInfo.class);
    }

    @Override
    public List<UserInfo> getAll() {
        return template.findAll(UserInfo.class);
    }
}
