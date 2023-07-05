package com.example.view.controllers;

import com.example.controller.ChatMenuMethods;
import com.example.model.User;
import com.example.model.UsersData;
import com.example.model.chat.*;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ChatSelectMenuController {
    private final UsersData usersData = UsersData.getInstance();
    private final DataChat dataChat = DataChat.getInstance();
    private final ChatMenuMethods chatMenuMethods = ChatMenuMethods.getInstance();
    @FXML
    private Label roomFindError;
    @FXML
    private TextField findRoomTextField;
    @FXML
    private Label roomError;
    @FXML
    private Label userError;
    @FXML
    private TextField createRoomTextField;
    @FXML
    private TextField userTextField;

    public void publicChat() throws IOException {
        chatMenuMethods.setPublicChat(dataChat.getPublicChat());
        Main.goToMenu("ChatMenu");
    }

    public void searchUser() throws IOException {
        String username = userTextField.getText();
        if (usersData.getUserByUsername(username) == null) {
            userError.setVisible(true);
            userError.setText("user doesn't exist");
            return;
        } else userError.setVisible(false);
        chatMenuMethods.setPrivateChat(dataChat.privateChat(usersData.getLoggedInUser().getUsername(), username));
        Main.goToMenu("ChatMenu");
    }

    public void createRoom() throws IOException {
        String roomName = createRoomTextField.getText();
        if (dataChat.getRoom(roomName) != null) {
            roomError.setVisible(true);
            roomError.setText("room already exists");
            return;
        } else roomError.setVisible(false);
        chatMenuMethods.setRoom(dataChat.newRoom(roomName, usersData.getLoggedInUser().getUsername()));
        Main.goToMenu("ChatMenu");
    }

    public void findRoom() throws IOException {
        String roomName = findRoomTextField.getText();
        Room room = dataChat.getRoom(roomName);
        if (room == null || !isInRoom(usersData.getLoggedInUser().getUsername(), room)) {
            roomFindError.setVisible(true);
            roomFindError.setText("room doesn't exist or you aren't in it");
            return;
        } else roomFindError.setVisible(false);
        chatMenuMethods.setRoom(room);
        Main.goToMenu("ChatMenu");
    }

    private boolean isInRoom(String username, Room room) {
        for (User user : room.getMembers())
            if (user.getUsername().equals(username))
                return true;
        return false;
    }
}
