package com.example.view.controllers;

import com.example.controller.ChatMenuMethods;
import com.example.model.chat.PublicChat;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ChatSelectMenuController {
    private final ChatMenuMethods chatMenuMethods = ChatMenuMethods.getInstance();
    @FXML
    private TextField createRoomTextField;
    @FXML
    private TextField userTextField;

    public void publicChat(MouseEvent mouseEvent) throws IOException {
        chatMenuMethods.setChat(PublicChat.getInstance());
        Main.goToMenu("ChatMenu");
    }

    public void searchUser(MouseEvent mouseEvent) {

    }

    public void createRoom(MouseEvent mouseEvent) {

    }
}
