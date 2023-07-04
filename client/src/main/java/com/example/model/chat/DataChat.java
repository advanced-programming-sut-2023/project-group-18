package com.example.model.chat;

import com.example.controller.NetworkController;
import com.example.model.IntegerWrapper;
import com.example.model.Request;
import com.example.model.User;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;

@XmlRootElement
public class DataChat {
    private final NetworkController networkController = NetworkController.getInstance();
    private static DataChat dataChat;

    public static DataChat getInstance() {
        return dataChat == null ? dataChat = new DataChat() : dataChat;
    }

    public Chat findChatById(int id) {
        return (Chat) networkController.transferData(new Request(DataChat.class, "findChatById", id));
    }

    public IntegerWrapper getNextId() {
        return (IntegerWrapper) networkController.transferData(new Request(DataChat.class, "getNextId"));
    }

    public void addChat(Chat chat) {
        networkController.transferData(new Request(DataChat.class, "addChat", chat));
    }

    public Chat privateChat(User user1, User user2) {
        return (Chat) networkController.transferData(new Request(DataChat.class, "privateChat", user1, user2));
    }

    public Room getRoom(String name) {
        return (Room) networkController.transferData(new Request(DataChat.class, "getRoom", name));
    }
}
