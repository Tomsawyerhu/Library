package com.example.demo.menu.dao;

import com.example.demo.menu.model.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StampDao extends JpaRepository<Stamp,Integer> {
    public Stamp getByUsernameAndIndexing(String username,String indexing);
    public List<Stamp> getAllByUsername(String username);
    public Stamp getByIndexing(String indexing);
}
