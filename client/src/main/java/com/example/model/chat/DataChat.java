package com.example.model.chat;

import com.example.controller.NetworkController;
import com.example.model.Request;
import jakarta.xml.bind.annotation.XmlRootElement;

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

    public PublicChat editPublicMessage(String time, String text) {
        return (PublicChat) networkController.transferData(new Request(DataChat.class, "editPublicMessage", time, text));
    }

    public PrivateChat editPrivateMessage(String time, String text, String username1, String username2) {
        return (PrivateChat) networkController.transferData(new Request(DataChat.class, "editPrivateMessage", time, text, username1, username2));
    }

    public Room editRoomMessage(String time, String text, String roomName) {
        return (Room) networkController.transferData(new Request(DataChat.class, "editRoomMessage", time, text, roomName));
    }

    public PublicChat deletePublicMessage(String time) {
        return (PublicChat) networkController.transferData(new Request(DataChat.class, "deletePublicMessage", time));
    }

    public PrivateChat deletePrivateMessage(String time, String username1, String username2) {
        return (PrivateChat) networkController.transferData(new Request(DataChat.class, "deletePrivateMessage", time, username1, username2));
    }

    public Room deleteRoomMessage(String time, String roomName) {
        return (Room) networkController.transferData(new Request(DataChat.class, "deleteRoomMessage", time, roomName));
    }
}