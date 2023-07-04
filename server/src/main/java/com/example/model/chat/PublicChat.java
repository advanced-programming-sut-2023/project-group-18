package com.example.model.chat;

import com.example.model.User;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PublicChat extends Chat{
    private static PublicChat single_instance = null;
    public PublicChat() {
        this.getMembers().addAll(User.getUsers());
    }
    //
    public static synchronized PublicChat getInstance() {
        if (single_instance == null)
            single_instance = new PublicChat();
        return single_instance;
    }
}
