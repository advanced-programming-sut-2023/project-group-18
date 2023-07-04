package com.example.model.chat;

import com.example.model.User;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;

@XmlRootElement
public class Room extends Chat{
    private String name;
    private User admin;
    
    public Room() {
    
    }

    public Room(String name, ArrayList<User> users, User admin) {
        this.name = name;
        this.getMembers().addAll(users);
        this.admin = admin;
    }
    public String getName() {
        return name;
    }

    public User getAdmin() {
        return admin;
    }
}