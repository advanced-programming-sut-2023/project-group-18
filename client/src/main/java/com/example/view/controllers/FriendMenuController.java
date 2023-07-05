package com.example.view.controllers;

import com.example.controller.ChatMenuMethods;
import com.example.model.User;
import com.example.model.UsersData;
import com.example.model.chat.Chat;
import com.example.model.chat.DataChat;
import com.example.model.chat.Message;
import com.example.model.chat.PublicChat;
import com.example.view.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Popup;

import java.io.IOException;

public class FriendMenuController {
    @FXML
    private TextField chatTextField;
    @FXML
    private BorderPane borderPane;
    @FXML
    private TextField userName;
    @FXML
    private VBox vBox;
    @FXML
    private VBox friendRequests;

    @FXML
    private VBox searchVBox;
    private final UsersData usersData = UsersData.getInstance();

    @FXML
    public void initialize() {
        vBox = new VBox();
        borderPane.setCenter(vBox);
    }

    public void back() throws IOException {
        Main.goToMenu("MainMenu");
    }

    public void searchUser(){
        String name = userName.getText();
        for (User user : User.getUsers()){
            if (user.getUsername().startsWith(name)){
                Label label = new Label(user.getUsername());
                Button requestButton = new Button("Request");
                HBox hBox = new HBox(label, requestButton);
                requestButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        requestButton.setText("Requested");
                        user.addRequest(UsersData.getInstance().getLoggedInUser());
                        requestButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                return;
                            }
                        });
                    }
                });
                hBox.setAlignment(Pos.CENTER);
                hBox.setSpacing(10);
                searchVBox.getChildren().add(hBox);
            }
        }
    }

    public void showRequests(){
        for (User user : UsersData.getInstance().getLoggedInUser().getRequests()){
            Label label = new Label(user.getUsername());
            Button acceptButton = new Button("Accept");
            Button declineButton = new Button("Decline");
            HBox hBox = new HBox(label, acceptButton, declineButton);
            friendRequests.getChildren().add(hBox);
            acceptButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    friendRequests.getChildren().removeAll(hBox);
                    UsersData.getInstance().getLoggedInUser().acceptRequest(user);
                }
            });
            declineButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    friendRequests.getChildren().removeAll(hBox);
                    UsersData.getInstance().getLoggedInUser().declineRequest(user);
                }
            });
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(10);
        }
    }
    public void refresh() {
        ChatMenuMethods chatMenuMethods = ChatMenuMethods.getInstance();
        vBox.getChildren().removeAll(vBox.getChildren());
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
