package com.example.model.chat;

import com.example.controller.NetworkController;
import com.example.model.IntegerWrapper;
import com.example.model.Request;
import com.example.model.User;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import java.util.ArrayList;

@XmlRootElement
@XmlSeeAlso({PublicChat.class, PrivateChat.class, Room.class})
public class Chat {
    @XmlElement
    private final ArrayList<User> members;
    @XmlElement
    private final ArrayList<Message> messages;
    @XmlElement
    private final static ArrayList<Chat> chats = new ArrayList<>();

    public Chat() {
        this.members = new ArrayList<>();
        this.messages = new ArrayList<>();
        chats.add(this);
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
//        NetworkController.getInstance().transferData(new Request(Chat.class, "addMessage", message));
        messages.add(message);
    }
}
