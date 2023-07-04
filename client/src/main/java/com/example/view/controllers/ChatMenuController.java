package com.example.view.controllers;

import com.example.model.UsersData;
import com.example.model.chat.Room;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ChatMenuController {
    @FXML
    private Label label;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button backButton;
    @FXML
    public void initialize() {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().add(label);
        vBox.getChildren().add(backButton);
        vBox.setAlignment(Pos.CENTER);
        Label publicChatLabel = new Label("Public Chat");
        Button openPublicChat = new Button("Go to public chat");
        HBox publicChatHBox = new HBox(publicChatLabel, openPublicChat);
        publicChatHBox.setAlignment(Pos.CENTER);
        publicChatHBox.setSpacing(10);
        Label privateChatLabel = new Label("Private Chat");
        TextField userSearch = new TextField("Enter Username");
        Button privateButton = new Button("Search");
        HBox privateChatHBox = new HBox(userSearch, privateButton);
        privateChatHBox.setAlignment(Pos.CENTER);
        privateChatHBox.setSpacing(10);
        Label roomLabel = new Label("Rooms");
        HBox RoomsHBox = new HBox();
        RoomsHBox.setAlignment(Pos.CENTER);
        RoomsHBox.setSpacing(10);
//        for (Room room : UsersData.getInstance().getLoggedInUser().getRooms()){
//            Button roomButton = new Button(room.getName());
//            RoomsHBox.getChildren().add(roomButton);
//        }
        TextField createRoomField = new TextField();
        createRoomField.setMaxWidth(200);
        Button createRoomButton = new Button("Create");
        HBox createRoomHBox = new HBox(createRoomField, createRoomButton);
        createRoomHBox.setAlignment(Pos.CENTER);
        //vBox.getChildren().add(textField);
        vBox.getChildren().addAll(publicChatHBox, privateChatLabel, privateChatHBox,roomLabel, RoomsHBox, createRoomHBox);
        scrollPane.setContent(vBox);
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        Main.goToMenu("MainMenu");
    }
}
