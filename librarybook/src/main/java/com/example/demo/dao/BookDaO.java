package com.example.demo.dao;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDaO extends JpaRepository<Book,Integer> {
    public Book findByNameAndCategory(String name,String category);
    public Book findByIndexing(String indexing);
    public Book findByName(String name);
    public List<Book> findByCategory(String category);
}
