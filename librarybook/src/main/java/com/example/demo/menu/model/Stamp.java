package com.example.demo.menu.model;
import javax.persistence.*;

@Entity
@Table(name = "stamp_")
public class Stamp {
    public Stamp(){}
    public Stamp(String username,String indexing){
        this.username=username;
        this.indexing=indexing;
    }
    public Stamp(String username,String indexing,int id){
        this.username=username;
        this.indexing=indexing;
        this.id=id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;            //id

    @Column(name = "username")
    private String username;

    @Column(name="indexing")
    private String indexing;

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getIndexing() {
        return indexing;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIndexing(String indexing) {
        this.indexing = indexing;
    }

    public void setId(int id) {
        this.id = id;
    }
}
