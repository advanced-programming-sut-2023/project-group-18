package com.example.model.chat;

import com.example.controller.NetworkController;
import com.example.model.Request;
import com.example.model.User;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PublicChat extends Chat{
    public PublicChat() {
        System.out.println("we are creating a publicChat");
        this.getMembers().addAll(User.getUsers());
    }
    //
    public static PublicChat getInstance() {
        //        if (single_instance == null)
//            single_instance = new PublicChat();
//        Chat chat = DataChat.getInstance().findChatById(0);
//        if (chat instanceof PublicChat) {
//            System.out.println("successful");
//        } else if (chat instanceof PrivateChat)
//            System.out.println("private chat");
//        else if (chat instanceof Room) {
//            System.out.println("room");
//        }
        return DataChat.getInstance().getPublicChat();
    }
}
