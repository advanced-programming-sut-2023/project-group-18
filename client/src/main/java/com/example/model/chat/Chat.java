package com.example.model.chat;

import com.example.controller.NetworkController;
import com.example.model.IntegerWrapper;
import com.example.model.Request;
import com.example.model.User;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import java.util.ArrayList;

@XmlRootElement
@XmlSeeAlso({PublicChat.class, PrivateChat.class, Room.class})
public class Chat {
    private final int id;
    private final ArrayList<User> members;
    private final ArrayList<Message> messages;
    private final static ArrayList<Chat> chats = new ArrayList<>();

    public Chat() {
        id = ((IntegerWrapper) NetworkController.getInstance().transferData(new Request(DataChat.class, "getNextId"))).getValue();
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

    public Integer getId() {
        return 0;
    }
}
