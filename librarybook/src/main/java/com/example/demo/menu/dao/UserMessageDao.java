package com.example.demo.menu.dao;

import com.example.demo.menu.model.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMessageDao extends JpaRepository<UserMessage,Integer> {
}
