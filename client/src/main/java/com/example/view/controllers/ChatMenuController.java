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

    @FXML
    public void initialize() {
        vBox = new VBox();
        refresh();
        borderPane.setCenter(vBox);
    }

    public void back() throws IOException {
        Main.goToMenu("MainMenu");
    }

    public void sendMessage() {
        String text = chatTextField.getText();
        chatTextField.setText("");
        Label label = new Label(usersData.getLoggedInUser().getUsername() + ":" + text);
        vBox.getChildren().add(label);
        if (chatMenuMethods.getChat() instanceof PublicChat) {
            System.out.println("chatMenuController publicChat instance");
            chatMenuMethods.setChat(DataChat.getInstance().addPublicMessage(usersData.getLoggedInUser().getUsername(), text));
        }
        chatMenuMethods.setChat(DataChat.getInstance().addMessage(usersData.getLoggedInUser().getUsername(), text, chatMenuMethods.getChat().getId()));
    }

    public void refresh() {
        vBox.getChildren().removeAll(vBox.getChildren());
        Chat chat = chatMenuMethods.getChat();
        if (chat instanceof Room) {
            System.out.println("chat is instance of room in chatselectmenucontroller.java");
        } else if (chat instanceof PublicChat) {
            System.out.println("chat instance of public chat");
        } else if (chat instanceof PrivateChat) {
            System.out.println("chat instance of private chat");
        } else System.out.println("chat is not instance of anything");
        if (chatMenuMethods.getChat() instanceof Room room) {
            System.out.println("testing room room");
            vBox.getChildren().add(new Label("Room name: " + room.getName()));
        }
        for (Message message : chatMenuMethods.getChat().getMessages()) {
            vBox.getChildren().add(new Label(message.getSender().getUsername() + ":" + message.getText()));
        }
    }
}
