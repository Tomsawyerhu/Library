package com.example.demo.dao.Impl;

import com.example.demo.dao.VisitInfoDao;
import com.example.demo.model.VisitInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Component
public class VisitInfoImpl implements VisitInfoDao {
    @Autowired
    MongoTemplate template;
    @Override
    public void add(VisitInfo info) {
        template.insert(info);
    }

    @Override
    public void delete(String userName,String time) {
        Criteria criteria=new Criteria();
        Query query=new Query().addCriteria(criteria.and("userName").is(userName).and("visitTime").is(time));
        template.remove(query);
    }

    @Override
    public void update(String userName,String time,VisitInfo info) {
        Criteria criteria=new Criteria();
        Query query=new Query().addCriteria(criteria.and("userName").is(userName).and("visitTime").is(time));
        Update update=new Update().set("userName",info.getUserName()).set("visitTime",info.getVisitTime());
        template.updateFirst(query,update,VisitInfo.class);

    }

    @Override
    public List<VisitInfo> get(String time) {
        Query query=new Query(Criteria.where("visitTime").is(time));
        return (List<VisitInfo>) template.find(query,VisitInfo.class);
    }

    @Override
    public List<VisitInfo> getAround(String hour) {
        Criteria criteria=new Criteria();
        Query query=new Query().addCriteria(criteria.and("visitTime").regex("* "+hour+":*:*"));
        return (List<VisitInfo>)template.find(query,VisitInfo.class);
    }

    @Override
    public List<VisitInfo> getByYearAndMonth(String year, String month) {
        String pattern=year+"-"+month;
        Query query=new Query(Criteria.where("visitTime").regex(pattern+"-*"));
        return template.find(query,VisitInfo.class);
    }

    @Override
    public List<VisitInfo> getByYear(String year) {
        String pattern=year+"-*";
        Query query=new Query(Criteria.where("visitTime").regex(pattern));
        return template.find(query,VisitInfo.class);
    }

    @Override
    public List<VisitInfo> getByCurrentDay() {
        Query query=new Query(Criteria.where("visitTime").regex(new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date()).split(" ")[0]+"*"));
        return template.find(query,VisitInfo.class);
    }

    @Override
    public void clear() {
        Criteria criteria=new Criteria();
        Query query=new Query(criteria.and("visitTime").regex("*"));
        template.remove(query,VisitInfo.class);
    }
}
