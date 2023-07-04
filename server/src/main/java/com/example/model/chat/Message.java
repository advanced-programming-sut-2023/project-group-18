package com.example.model.chat;

import com.example.model.User;

import java.io.File;
import java.util.ArrayList;

public class Message {
    private final User sender;
    private final String time;
    private String text;
    private final File avatar;
    private boolean hasSent;
    private boolean hasSeen;
    private final ArrayList<Reaction> reactions;

    public Message(User sender, String text) {
        this.sender = sender;
        this.text = text;
        this.reactions = new ArrayList<>();
        this.avatar = sender.getAvatar();
        this.time = "";
        hasSent = false;
        hasSeen = false;
    }

    public User getSender() {
        return sender;
    }

    public String getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public File getAvatar() {
        return avatar;
    }

    public boolean isHasSent() {
        return hasSent;
    }

    public boolean isHasSeen() {
        return hasSeen;
    }

    public ArrayList<Reaction> getReactions() {
        return reactions;
    }

    public void addReact(Reaction reaction){
        reactions.add(reaction);
    }

    public void editText(String text){
        this.text = text;
    }

    public void setHasSent(boolean hasSent) {
        this.hasSent = hasSent;
    }

    public void setHasSeen(boolean hasSeen) {
        this.hasSeen = hasSeen;
    }


}
