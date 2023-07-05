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


    public Room getRoom(String name) {
        return (Room) networkController.transferData(new Request(DataChat.class, "getRoom", name));
    }

    public PublicChat addPublicMessage(String username, String text) {
        return (PublicChat) networkController.transferData(new Request(DataChat.class, "addPublicMessage", username, text));
    }

    public PrivateChat addPrivateMessage(String sender, String text, String username1, String username2) {
        return (PrivateChat) networkController.transferData(new Request(DataChat.class, "addPrivateMessage", sender, text, username1, username2));
    }

    public Room addRoomMessage(String username, String text, String roomName) {
        return (Room) networkController.transferData(new Request(DataChat.class, "addRoomMessage", username, text, roomName));
    }

    public PublicChat getPublicChat() {
        return (PublicChat) networkController.transferData(new Request(DataChat.class, "getPublicChat"));
    }

    public PrivateChat privateChat(String username1, String username2) {
        return (PrivateChat) networkController.transferData(new Request(DataChat.class, "privateChat", username1, username2));
    }

    public Room newRoom(String roomName, String username) {
        return (Room) networkController.transferData(new Request(DataChat.class, "newRoom", roomName, username));
    }
}