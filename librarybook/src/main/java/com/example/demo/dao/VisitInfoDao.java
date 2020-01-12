package com.example.demo.dao;

import com.example.demo.model.VisitInfo;

import java.util.List;

public interface VisitInfoDao {
    void add(VisitInfo info);
    void delete(String userName,String time);
    void update(String userName,String time,VisitInfo info);
    List<VisitInfo> get(String time);
    List<VisitInfo> getAround(String hour);
    List<VisitInfo> getByYearAndMonth(String year,String month);
    List<VisitInfo> getByYear(String year);
    List<VisitInfo> getByCurrentDay();
    void clear();
}
