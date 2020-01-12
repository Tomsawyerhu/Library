package com.example.demo.index.model;

import com.example.demo.user.model.UserSession;

import java.util.Date;

public class TokenUtil {
    TokenUtil(){}
    public static Token createToken(String startTime,String username,String sessionId){
        return new Token( String.valueOf(new Date().getTime()),username,sessionId);
    }
    public static UserSession parseToken(Token token){
        return new UserSession(token.getSessionId(),token.getUsername(),String.valueOf(new Date().getTime()));
    }

}
