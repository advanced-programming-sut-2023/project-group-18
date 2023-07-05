package com.example.controller;

import com.example.model.Request;
import com.example.model.chat.*;

public class ChatMenuMethods {
    private static ChatMenuMethods chatMenuMethods;
    private PublicChat publicChat = null;
    private PrivateChat privateChat = null;
    private Room room = null;

    public static ChatMenuMethods getInstance() {
        return chatMenuMethods == null ? chatMenuMethods = new ChatMenuMethods() : chatMenuMethods;
    }

    public PublicChat getPublicChat() {
        return publicChat;
    }

    public PrivateChat getPrivateChat() {
        return privateChat;
    }

    public Room getRoom() {
        return room;
    }

    public void setPublicChat(PublicChat publicChat) {
        this.publicChat = publicChat;
    }

    public void setPrivateChat(PrivateChat privateChat) {
        this.privateChat = privateChat;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
