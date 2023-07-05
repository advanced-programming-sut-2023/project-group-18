package com.example.model.chat;

import com.example.model.User;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PrivateChat extends Chat {
    public PrivateChat() {
    }

    public PrivateChat(User user1, User user2) {
        super();
        this.getMembers().add(user1);
        this.getMembers().add(user2);
    }
}
