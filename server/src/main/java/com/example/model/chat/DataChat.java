package com.example.model.chat;

import com.example.model.IntegerWrapper;
import com.example.model.User;
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

    public Chat findChatById(int id) {
        for (Chat chat : chats)
            if (chat.getId() == id)
                return chat;
        return null;
    }

    public IntegerWrapper getNextId() {
        return new IntegerWrapper(chats.size());
    }

    public void addChat(Chat chat) {
        chats.add(chat);
    }

    public Chat privateChat(User user1, User user2) {
        for (Chat chat : chats)
            if (chat instanceof PrivateChat && chat.getMembers().contains(user1) && chat.getMembers().contains(user2))
                return chat;
        return new PrivateChat(user1, user2);
    }

    public Room getRoom(String name) {
        for (Chat chat : chats)
            if (chat instanceof Room room)
                if (room.getName().equals(name))
                    return room;
        return null;
    }
}
