package com.example.model.chat;

import com.example.model.IntegerWrapper;
import com.example.model.User;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

@XmlRootElement
@XmlSeeAlso({ PublicChat.class, PrivateChat.class, Room.class})
public class Chat {
    @XmlElement
    private final CopyOnWriteArrayList<User> members;
    @XmlElement
    private final CopyOnWriteArrayList<Message> messages;

    public Chat() {
        DataChat dataChat = DataChat.getInstance();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        this.members = new CopyOnWriteArrayList<>();
        this.messages = new CopyOnWriteArrayList<>();
    }


    public CopyOnWriteArrayList<User> getMembers() {
        return members;
    }

    public CopyOnWriteArrayList<Message> getMessages() {
        return messages;
    }

    public void addMember(User user) {
        members.add(user);
    }

    public void deleteMessageForMe(Message message) {

    }

    public void deleteMessageForAll(Message message) {
        messages.remove(message);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}