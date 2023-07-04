package com.example.controller;

import com.example.model.Request;
import com.example.model.chat.Chat;
import com.example.model.chat.DataChat;

public class ChatMenuMethods {
    private static ChatMenuMethods chatMenuMethods;
    private Chat chat;
    public static ChatMenuMethods getInstance() {
        return chatMenuMethods == null ? chatMenuMethods = new ChatMenuMethods() : chatMenuMethods;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Chat getChat() {
//        chat = DataChat.getInstance().findChatById(chat.getId());
        return chat;
    }
}
