package com.example.demo.user.dao;

import com.example.demo.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDaO extends JpaRepository<User,Integer> {
    public List<User> getAllByUsername(String username);
    public User getByUsernameAndPassword(String username,String password);
}
