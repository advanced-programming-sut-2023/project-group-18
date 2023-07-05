package com.example.model.chat;

import com.example.model.IntegerWrapper;
import com.example.model.Request;
import com.example.model.User;
import com.example.model.UsersData;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

@XmlRootElement
public class DataChat {
    private final UsersData usersData = UsersData.getInstance();
    private final PublicChat publicChat = PublicChat.getInstance();
    private final CopyOnWriteArrayList<PrivateChat> privateChats = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Room> rooms = new CopyOnWriteArrayList<>();

    private static DataChat dataChat;

    public static DataChat getInstance() {
        if (dataChat == null) {
            dataChat = new DataChat();
        }
        return dataChat;
    }

    public synchronized Room getRoom(String name) {
        synchronized (rooms) {
            for (Room room : rooms)
                if (room.getName().equals(name))
                    return room;
            return null;
        }
    }

    public PublicChat addPublicMessage(String username, String text) {
        publicChat.addMessage(new Message(usersData.getUserByUsername(username), text));
        return publicChat;
    }

    public PrivateChat addPrivateMessage(String sender, String text, String username1, String username2) {
        PrivateChat privateChat = privateChat(username1, username2);
        privateChat.addMessage(new Message(usersData.getUserByUsername(sender), text));
        return privateChat;
    }

    public Room addRoomMessage(String username, String text, String roomName) {
        Room room = getRoom(roomName);
        room.addMessage(new Message(usersData.getUserByUsername(username), text));
        return room;
    }

    public PublicChat getPublicChat() {
        return publicChat;
    }

    public synchronized PrivateChat privateChat(String username1, String username2) {
        synchronized (privateChats) {
            User user1 = usersData.getUserByUsername(username1);
            User user2 = usersData.getUserByUsername(username2);
            for (PrivateChat privateChat : privateChats) {
                System.out.println("input user 1 is: " + username1);
                System.out.println("input user 2 is: " + username2);
                System.out.println("private chat's first user is: " + privateChat.getMembers().get(0).getUsername());
                System.out.println("private chat's second user is: " + privateChat.getMembers().get(1).getUsername());
                if ((privateChat.getMembers().get(0).getUsername().equals(username1) && privateChat.getMembers().get(1).getUsername().equals(username2)) ||
                        (privateChat.getMembers().get(1).getUsername().equals(username1) && privateChat.getMembers().get(0).getUsername().equals(username2)))
                    return privateChat;
            }
            System.out.println("we didn't find a private chat");
            PrivateChat privateChat = new PrivateChat(user1, user2);
            privateChats.add(privateChat);
            return privateChat;
        }
    }


    public Room newRoom(String roomName, String username) {
        User admin = usersData.getUserByUsername(username);
        ArrayList<User> arrayList = new ArrayList<>();
        arrayList.add(admin);
        return new Room(roomName, arrayList, admin);
    }
}
