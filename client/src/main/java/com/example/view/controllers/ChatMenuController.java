package com.example.view.controllers;

import com.example.controller.ChatMenuMethods;
import com.example.model.UsersData;
import com.example.model.chat.*;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;

public class ChatMenuController {
    @FXML
    private TextField chatTextField;
    @FXML
    private BorderPane borderPane;
    private VBox vBox;
    private final UsersData usersData = UsersData.getInstance();
    private final ChatMenuMethods chatMenuMethods = ChatMenuMethods.getInstance();
    private final DataChat dataChat = DataChat.getInstance();

    @FXML
    public void initialize() {
        vBox = new VBox();
        refresh();
        borderPane.setCenter(vBox);
    }

    public void back() throws IOException {
        chatMenuMethods.setPublicChat(null);
        chatMenuMethods.setPrivateChat(null);
        chatMenuMethods.setRoom(null);
        Main.goToMenu("MainMenu");
    }

    public void sendMessage() {
        String text = chatTextField.getText();
        chatTextField.setText("");
        Label label = new Label(usersData.getLoggedInUser().getUsername() + ":" + text);
        vBox.getChildren().add(label);
        if (chatMenuMethods.getPublicChat() != null) {
            chatMenuMethods.setPublicChat(dataChat.addPublicMessage(usersData.getLoggedInUser().getUsername(), text));
        } else if (chatMenuMethods.getPrivateChat() != null) {
            chatMenuMethods.setPrivateChat(dataChat.addPrivateMessage(usersData.getLoggedInUser().getUsername(), text,
                    chatMenuMethods.getPrivateChat().getMembers().get(0).getUsername(), chatMenuMethods.getPrivateChat().getMembers().get(1).getUsername()));
        } else if (chatMenuMethods.getRoom() != null) {
            chatMenuMethods.setRoom(dataChat.addRoomMessage(usersData.getLoggedInUser().getUsername(), text, chatMenuMethods.getRoom().getName()));
        }

    }

    public void refresh() {
        vBox.getChildren().removeAll(vBox.getChildren());
        if (chatMenuMethods.getRoom() != null) {
            vBox.getChildren().add(new Label("Room name: " + chatMenuMethods.getRoom().getName()));
        }
        Chat chat = null;
        if (chatMenuMethods.getPublicChat() != null) {
            chat = chatMenuMethods.getPublicChat();
        } else if (chatMenuMethods.getPrivateChat() != null) {
            chat = chatMenuMethods.getPrivateChat();
        } else if (chatMenuMethods.getRoom() != null) {
            chat = chatMenuMethods.getRoom();
        }
        for (Message message : chat.getMessages()) {
            vBox.getChildren().add(new Label(message.getSender().getUsername() + ":" + message.getText()));
        }
    }
}
