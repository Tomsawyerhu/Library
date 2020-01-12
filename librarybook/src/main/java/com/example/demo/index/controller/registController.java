package com.example.demo.index.controller;

import com.example.demo.dao.UserInfoDao;
import com.example.demo.model.OtherInfo;
import com.example.demo.model.UserInfo;
import com.example.demo.user.dao.UserDaO;
import com.example.demo.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class registController {
    @Autowired
    UserDaO userDAO;
    @Autowired
    UserInfoDao dao;
    @RequestMapping("/redirect")
    public String redirect(){
        return "regist";
    }

    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    @ResponseBody
    public String regist(@RequestParam(name="tele")String tele, @RequestParam(name="username")String username, @RequestParam(name="password")String password){
        List<User> list=userDAO.getAllByUsername(username);
        User user=new User(username,password,tele);
        //System.out.print(tele);
        if (list.isEmpty()) {
            userDAO.save(user);
            SimpleDateFormat format=new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            UserInfo userInfo=new UserInfo(username,format.format(new Date()),tele,new OtherInfo());
            dao.add(userInfo);
            return  "success";
        }
        else
            return "failure";

    }
}
