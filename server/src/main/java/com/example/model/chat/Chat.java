package com.example.model.chat;

import com.example.model.User;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import java.util.ArrayList;

@XmlRootElement
@XmlSeeAlso({ PublicChat.class, PrivateChat.class, Room.class})
public class Chat {
    private final DataChat dataChat = DataChat.getInstance();
    @XmlElement
    private final int id;
    @XmlElement
    private final ArrayList<User> members;
    @XmlElement
    private final ArrayList<Message> messages;

    public Chat() {
        id = dataChat.getNextId().getValue();
        this.members = new ArrayList<>();
        this.messages = new ArrayList<>();
        dataChat.addChat(this);
    }


    public ArrayList<User> getMembers() {
        return members;
    }

    public ArrayList<Message> getMessages() {
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

    public int getId() {
        return id;
    }

}