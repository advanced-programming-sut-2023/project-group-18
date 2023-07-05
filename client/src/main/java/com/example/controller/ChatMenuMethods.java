package com.example.controller;

import com.example.model.Request;
import com.example.model.chat.Chat;
import com.example.model.chat.DataChat;
import com.example.model.chat.PublicChat;

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
        System.out.println("chat id is: " + chat.getId());
        if (chat instanceof PublicChat)
            chat = DataChat.getInstance().getPublicChat();
        else
            chat = DataChat.getInstance().findChatById(chat.getId());
        System.out.println("chat id is: " + chat.getId());
        return chat;
    }
}
