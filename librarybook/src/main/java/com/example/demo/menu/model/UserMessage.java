package com.example.demo.menu.model;

import javax.persistence.*;

@Entity
@Table(name="userMessage_")
public class UserMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;            //id

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;
    @Column(name="message")
    private String message;
    public UserMessage(String name,String email,String message){
        this.name=name;
        this.email=email;
        this.message=message;
    }
    public UserMessage(){}

    public String toString(){
        return String.format("Name:%s\nEmail:%s\nMessage:%s",name,email,message);
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setMessage(String message){
        this.message=message;
    }
    public void setEmail(String email){
        this.email=email;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getMessage(){
        return message;
    }

}
