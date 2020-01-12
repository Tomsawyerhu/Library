package com.example.demo;

import com.example.demo.index.model.Token;
import com.example.demo.index.model.TokenUtil;
import com.example.demo.user.dao.UserSessionDaO;
import com.example.demo.user.model.UserSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component

public class UserInterceptor implements HandlerInterceptor {
   private UserSessionDaO daO;
    public UserInterceptor(UserSessionDaO daO){this.daO=daO;}


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        System.out.println("接收到请求");


        HttpSession session=request.getSession();
        if(session.getAttribute("token")==null) {
            try {
                response.sendRedirect(request.getContextPath()+"/");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        UserSession userSession1= TokenUtil.parseToken((Token) session.getAttribute("token"));
        String str1=((Token) session.getAttribute("token")).getSessionId();
        String str2=((Token) session.getAttribute("token")).getUsername();
        UserSession userSession2=daO.getBySessionIdAndUsername(str1,str2);
        if(!userSession2.isExpire()) return true;
        else {
            try {
                response.sendRedirect(request.getContextPath()+"/");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;}
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {}
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e)throws Exception{ }

}
