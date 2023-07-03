package com.example.model.chat;

import com.example.model.User;

public class PublicChat extends Chat{
    public PublicChat() {
        this.getMembers().addAll(User.getUsers());
    }
}
