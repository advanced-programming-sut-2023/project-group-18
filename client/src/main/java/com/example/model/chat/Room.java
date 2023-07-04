package com.example.model.chat;

import com.example.model.User;

import java.util.ArrayList;

public class Room extends Chat{
    private final String name;
    private final User admin;

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
