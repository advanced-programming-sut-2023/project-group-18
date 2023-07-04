package com.example.model.chat;

import com.example.model.User;

import java.util.ArrayList;

public class Room extends Chat{
    private final String name;

    public Room(String name, ArrayList<User> users) {
        this.name = name;
        this.getMembers().addAll(users);
    }
    public String getName() {
        return name;
    }
}
