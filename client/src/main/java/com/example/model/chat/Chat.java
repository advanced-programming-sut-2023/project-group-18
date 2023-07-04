package com.example.model.chat;

import com.example.model.User;

import java.util.ArrayList;

public class Chat {
    private final ArrayList<User> members;
    private final ArrayList<Message> messages;
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

    public void addMember(User user){
        members.add(user);
    }

    public void deleteMessageForMe(Message message){

    }

    public void deleteMessageForAll(Message message){
        messages.remove(message);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }


}
