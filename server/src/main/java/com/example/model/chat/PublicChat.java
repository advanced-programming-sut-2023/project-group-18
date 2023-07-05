package com.example.model.chat;

import com.example.model.User;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PublicChat extends Chat{
    private static final PublicChat single_instance = new PublicChat();
    public PublicChat() {
        System.out.println("we are creating a publicChat");
        this.getMembers().addAll(User.getUsers());
    }
    //
    public static synchronized PublicChat getInstance() {
        return single_instance;
    }
}
