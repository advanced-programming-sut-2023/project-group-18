package com.example.model.chat;

import com.example.model.User;

public class PrivateChat extends Chat{
    public PrivateChat(User user1, User user2) {
        super();
        this.getMembers().add(user1);
        this.getMembers().add(user2);
    }
}
