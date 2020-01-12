package com.example.demo.bean.Component;

import com.example.demo.bean.service.VisitCollectService;
import com.example.demo.index.model.Token;
import com.example.demo.model.VisitInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class VisitListener implements ServletRequestListener {
    @Autowired
    private VisitCollectService service;
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        if(((HttpServletRequest)sre.getServletRequest()).getSession().getAttribute("token")!=null){
        VisitInfo info=new VisitInfo();
        info.setUserName(((Token)((HttpServletRequest)sre.getServletRequest()).getSession().getAttribute("token")).getUsername());
        SimpleDateFormat format=new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        info.setVisitTime(format.format(new Date()));
        service.collect(info);
        }
    }
}
