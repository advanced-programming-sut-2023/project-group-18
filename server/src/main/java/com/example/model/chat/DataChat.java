package com.example.model.chat;

import com.example.controller.ChatMenuMethods;
import com.example.model.IntegerWrapper;
import com.example.model.Request;
import com.example.model.User;
import com.example.model.UsersData;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

@XmlRootElement
public class DataChat {
    private final PublicChat publicChat = PublicChat.getInstance();
    private final CopyOnWriteArrayList<Chat> chats = new CopyOnWriteArrayList<>();

    private static DataChat dataChat;

    public static DataChat getInstance() {
        if (dataChat == null) {
            dataChat = new DataChat();
        }
        return dataChat;
    }

    public synchronized Chat findChatById(int id) {
        synchronized (chats) {
            for (Chat chat : chats)
                if (chat.getId() == id) {
                    return chat;
                }
            return null;
        }
    }

    public synchronized IntegerWrapper getNextId() {
        synchronized (chats) {
            return new IntegerWrapper(chats.size());
        }
    }

    public synchronized void addChat(Chat chat) {
        synchronized (chats) {
            chats.add(chat);
        }
    }

    public synchronized Chat privateChat(String username1, String username2) {
        synchronized (chats) {
            User user1 = UsersData.getInstance().getUserByUsername(username1);
            User user2 = UsersData.getInstance().getUserByUsername(username2);
            for (Chat chat : chats)
                if (chat instanceof PrivateChat && chat.getMembers().contains(user1) && chat.getMembers().contains(user2))
                    return chat;
            return new PrivateChat(user1, user2);
        }
    }

    public synchronized Room getRoom(String name) {
        synchronized (chats) {
            for (Chat chat : chats)
                if (chat instanceof Room room)
                    if (room.getName().equals(name))
                        return room;
            return null;
        }
    }

    public synchronized void replaceChat(Chat chat) {
        synchronized (chats) {
        chats.set(chat.getId(), chat);
        }
    }

    public synchronized Chat addMessage(String username, String text, int id) {
        Chat chat = findChatById(id);
        chat.addMessage(new Message(UsersData.getInstance().getUserByUsername(username), text));
        System.out.println("chat size is: " + chat.getMessages().size());
        return chat;
    }

    public Room newRoom(String roomName, String username) {
        User admin = UsersData.getInstance().getUserByUsername(username);
        ArrayList<User> arrayList = new ArrayList<>();
        arrayList.add(admin);
        return new Room(roomName, arrayList, admin);
    }

    public PublicChat getPublicChat() {
        System.out.println(publicChat.getId()+"kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        return publicChat;
    }

    public PublicChat addPublicMessage(String username, String text) {
        publicChat.addMessage(new Message(UsersData.getInstance().getUserByUsername(username), text));
        return publicChat;
    }
}
