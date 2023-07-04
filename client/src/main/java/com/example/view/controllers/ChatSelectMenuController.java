package com.example.view.controllers;

import com.example.controller.ChatMenuMethods;
import com.example.model.UsersData;
import com.example.model.chat.Chat;
import com.example.model.chat.DataChat;
import com.example.model.chat.PublicChat;
import com.example.model.chat.Room;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ChatSelectMenuController {
    private final UsersData usersData = UsersData.getInstance();
    private final ChatMenuMethods chatMenuMethods = ChatMenuMethods.getInstance();
    @FXML
    private Label roomError;
    @FXML
    private Label userError;
    @FXML
    private TextField createRoomTextField;
    @FXML
    private TextField userTextField;

    public void publicChat() throws IOException {
        chatMenuMethods.setChat(PublicChat.getInstance());
        Main.goToMenu("ChatMenu");
    }

    public void searchUser(MouseEvent mouseEvent) throws IOException {
        String username = userTextField.getText();
        if (usersData.getUserByUsername(username) == null) {
            userError.setVisible(true);
            userError.setText("user doesn't exist");
            return;
        } else userError.setVisible(false);
        chatMenuMethods.setChat(DataChat.getInstance().privateChat(usersData.getLoggedInUser(), usersData.getUserByUsername(username)));
        Main.goToMenu("ChatMenu");
    }

    public void createRoom(MouseEvent mouseEvent) {
        String roomName = createRoomTextField.getText();
        if (DataChat.getInstance().getRoom(roomName) != null) {
            roomError.setVisible(true);
            roomError.setText("room already exists");
            return;
        } else roomError.setVisible(false);
//        chatMenuMethods.setChat(new Room());
    }
}
