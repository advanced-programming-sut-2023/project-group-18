package com.example.model.chat;

import com.example.controller.ChatMenuMethods;
import com.example.model.IntegerWrapper;
import com.example.model.Request;
import com.example.model.User;
import com.example.model.UsersData;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;

@XmlRootElement
public class DataChat {
    private final ArrayList<Chat> chats = new ArrayList<>();

    private static DataChat dataChat;

    public static DataChat getInstance() {
        if (dataChat == null) {
            dataChat = new DataChat();
            PublicChat.getInstance();
        }
        return dataChat == null ? dataChat = new DataChat() : dataChat;
    }

    public synchronized Chat findChatById(int id) {
        for (Chat chat : chats)
            if (chat.getId() == id) {
                return chat;
            }
        return null;
    }

    public synchronized IntegerWrapper getNextId() {
        return new IntegerWrapper(chats.size());
    }

    public synchronized void addChat(Chat chat) {
        chats.add(chat);
    }

    public synchronized Chat privateChat(User user1, User user2) {
        for (Chat chat : chats)
            if (chat instanceof PrivateChat && chat.getMembers().contains(user1) && chat.getMembers().contains(user2))
                return chat;
        return new PrivateChat(user1, user2);
    }

    public synchronized Room getRoom(String name) {
        for (Chat chat : chats)
            if (chat instanceof Room room)
                if (room.getName().equals(name))
                    return room;
        return null;
    }

    public synchronized void replaceChat(Chat chat) {
        chats.set(chat.getId(), chat);
    }

    public synchronized Chat addMessage(String username, String text, int id) {
        Chat chat = findChatById(id);
        chat.addMessage(new Message(UsersData.getInstance().getUserByUsername(username), text));
        System.out.println("chat size is: " + chat.getMessages().size());
        return chat;
    }
}
