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
    private VBox friendRequests;

    @FXML
    private VBox searchVBox;
    private final UsersData usersData = UsersData.getInstance();

    @FXML
    public void initialize() {
    }

    public void back() throws IOException {
        Main.goToMenu("MainMenu");
    }

    public void searchUser() {
        if (searchVBox.getChildren().size() != 3)
            searchVBox.getChildren().remove(searchVBox.getChildren().get(searchVBox.getChildren().size() - 1));
        String name = userName.getText();
        User user = usersData.getUserByUsername(name);
        if (user != null) {
            Label label = new Label(user.getUsername());
            Button requestButton = new Button("Request");
            HBox hBox = new HBox(label, requestButton);
            label.setOnMouseClicked(mouseEvent -> {
                usersData.setSeeProfileUser(user);
                try {
                    Main.goToMenu("ProfileFriendMenu");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            requestButton.setOnMouseClicked(mouseEvent -> {
                requestButton.setText("Requested");
                usersData.request(usersData.getLoggedInUser().getUsername(), name);
                userName.setText("");
                requestButton.setOnMouseClicked(mouseEvent1 -> {
                });
            });
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(10);
            searchVBox.getChildren().add(hBox);
        }
    }

    public void showRequests() {
        for (User user : UsersData.getInstance().getLoggedInUser().getRequests()) {
            System.out.println("we have a request from someone");
            Label label = new Label(user.getUsername());
            Button acceptButton = new Button("Accept");
            Button declineButton = new Button("Decline");
            HBox hBox = new HBox(label, acceptButton, declineButton);
            friendRequests.getChildren().add(hBox);
            acceptButton.setOnMouseClicked(mouseEvent -> {
                friendRequests.getChildren().removeAll(hBox);
                UsersData.getInstance().getLoggedInUser().acceptRequest(user);
            });
            declineButton.setOnMouseClicked(mouseEvent -> {
                friendRequests.getChildren().removeAll(hBox);
                UsersData.getInstance().getLoggedInUser().declineRequest(user);
            });
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(10);
        }
    }
}
