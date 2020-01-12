package com.example.demo.bean.service;


import com.example.demo.dao.VisitInfoDao;
import com.example.demo.model.VisitInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class VisitCollectService {
    @Autowired
    VisitInfoDao dao;


    public void collect(VisitInfo info){//保存在mongodb中
        dao.add(info);
    }

   /* @Scheduled(cron = "0 0 0 * * ? ")
    public void clear(){
        //存储到日点击量数据库

        //每天零点清空访问信息
        dao.clear();
    }*/
}
