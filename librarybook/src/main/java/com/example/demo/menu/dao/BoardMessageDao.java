package com.example.demo.menu.dao;

import com.example.demo.menu.model.BoardMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardMessageDao extends JpaRepository<BoardMessage,Integer> {
}
