package com.example.demo.user.dao;

import com.example.demo.user.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository
public interface UserSessionDaO extends JpaRepository<UserSession,Integer> {
    public UserSession getBySessionIdAndUsername(String sessionId,String username);
    public UserSession getByUsername( String username);
    public List<UserSession> getAllByUsername( String username);

}
