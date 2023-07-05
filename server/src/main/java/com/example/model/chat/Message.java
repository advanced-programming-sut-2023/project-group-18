package com.example.model.chat;

import com.example.model.User;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.File;
import java.util.ArrayList;

@XmlRootElement
public class Message {
    @XmlElement
    private User sender;
    @XmlElement
    private String time;
    @XmlElement
    private String text;
    //    @XmlElement
//    private boolean hasSent;
//    @XmlElement
//    private boolean hasSeen;
//    @XmlElement
//    private ArrayList<Reaction> reactions;
    public Message() {

    }

    public Message(User sender, String text) {
        this.sender = sender;
        this.text = text;
//        this.reactions = new ArrayList<>();
        this.time = "";
//        hasSent = false;
//        hasSeen = false;
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

//    public boolean getHasSent() {
//        return hasSent;
//    }
//
//    public boolean getHasSeen() {
//        return hasSeen;
//    }

//    public ArrayList<Reaction> getReactions() {
//        return reactions;
//    }

//    public void addReact(Reaction reaction){
//        reactions.add(reaction);
//    }

    public void editText(String text){
        this.text = text;
    }

//    public void setHasSent(boolean hasSent) {
//        this.hasSent = hasSent;
//    }
//
//    public void setHasSeen(boolean hasSeen) {
//        this.hasSeen = hasSeen;
//    }


}
