package com.example.view.controllers;

import com.example.controller.ChatMenuMethods;
import com.example.model.UsersData;
import com.example.model.chat.Chat;
import com.example.model.chat.DataChat;
import com.example.model.chat.Message;
import com.example.model.chat.PublicChat;
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
        ChatMenuMethods chatMenuMethods = ChatMenuMethods.getInstance();
        String text = chatTextField.getText();
        chatTextField.setText("");
        Label label = new Label(usersData.getLoggedInUser().getNickname() + ":" + text);
        vBox.getChildren().add(label);
//        System.out.println(chatMenuMethods.getChat().getId());
        chatMenuMethods.setChat(DataChat.getInstance().addMessage(usersData.getLoggedInUser().getUsername(), text, chatMenuMethods.getChat().getId()));
//        System.out.println("chat size is: " + chatMenuMethods.getChat().getMessages().size());
//        Chat chat = ChatMenuMethods.getInstance().getChat();
//        chat.addMessage(new Message(usersData.getLoggedInUser(), text));
//        DataChat.getInstance().replaceChat(chat);
//        PublicChat.getInstance().addMessage(new Message(usersData.getLoggedInUser(), text));
    }

    public void refresh() {
        vBox.getChildren().removeAll(vBox.getChildren());
        for (Message message : ChatMenuMethods.getInstance().getChat().getMessages()) {
            vBox.getChildren().add(new Label(message.getText()));
        }
    }
}
