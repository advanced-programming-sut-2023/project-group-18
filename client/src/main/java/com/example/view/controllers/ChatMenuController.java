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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ChatMenuController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label label;
    private ScrollPane scrollPane = new ScrollPane();
    @FXML
    private Button backButton;
    @FXML
    public void initialize() {
        Label bottomLabel = new Label("Static node at the bottom");

// Set the content of the ScrollPane
        VBox vBox = new VBox();
// Add your content to the VBox
// ...
        scrollPane.setContent(vBox);

// Set the anchor constraints of the ScrollPane to fill the entire AnchorPane
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, bottomLabel.getHeight());
        AnchorPane.setLeftAnchor(scrollPane, 0.0);

// Set the anchor constraints of the static node to be at the bottom of the AnchorPane
        AnchorPane.setTopAnchor(bottomLabel, anchorPane.getHeight() - bottomLabel.getHeight());
        AnchorPane.setRightAnchor(bottomLabel, 0.0);
        AnchorPane.setBottomAnchor(bottomLabel, 0.0);
        AnchorPane.setLeftAnchor(bottomLabel, 0.0);

// Add the ScrollPane and the static node to the AnchorPane
        anchorPane.getChildren().addAll(scrollPane, bottomLabel);











//        AnchorPane.setTopAnchor(scrollPane, 0.0);
//        AnchorPane.setRightAnchor(scrollPane, 0.0);
//        AnchorPane.setBottomAnchor(scrollPane, 100.0);
//        AnchorPane.setLeftAnchor(scrollPane, 0.0);
//
//        VBox vBox = new VBox();
//        vBox.setSpacing(10);
//        vBox.getChildren().add(label);
//        vBox.getChildren().add(backButton);
//        vBox.getChildren().addAll(new Label("testing something"),new Label("testing something"),new Label("testing something"),
//                new Label("testing something"),new Label("testing something"),new Label("testing something"),
//                new Label("testing something"),new Label("testing something"),new Label("testing something"),
//                new Label("testing something"),new Label("testing something"),new Label("testing something"),
//                new Label("testing something"),new Label("testing something"),new Label("testing something"),
//                new Label("testing something"),new Label("testing something"),new Label("testing something"),
//                new Label("testing something"),new Label("testing something"),new Label("testing something"),
//                new Label("testing something"),new Label("testing something"),new Label("testing something"),
//                new Label("testing something"),new Label("testing something"),new Label("testing something"));
//        vBox.setAlignment(Pos.CENTER);
//        Label test = new Label("testing");
//        AnchorPane.setTopAnchor(test, anchorPane.getHeight() - test.getHeight());
//        AnchorPane.setRightAnchor(test, 0.0);
//        AnchorPane.setBottomAnchor(test, 0.0);
//        AnchorPane.setLeftAnchor(test, 0.0);
////        VBox.setVgrow(vBox, Priority.ALWAYS);
////        vBox.getChildren().add(test);
//        scrollPane.setContent(vBox);
//        anchorPane.getChildren().addAll(label, backButton, vBox, test);





//        Label publicChatLabel = new Label("Public Chat");
//        Button openPublicChat = new Button("Go to public chat");
//        HBox publicChatHBox = new HBox(publicChatLabel, openPublicChat);
//        publicChatHBox.setAlignment(Pos.CENTER);
//        publicChatHBox.setSpacing(10);
//        Label privateChatLabel = new Label("Private Chat");
//        TextField userSearch = new TextField("Enter Username");
//        Button privateButton = new Button("Search");
//        HBox privateChatHBox = new HBox(userSearch, privateButton);
//        privateChatHBox.setAlignment(Pos.CENTER);
//        privateChatHBox.setSpacing(10);
//        Label roomLabel = new Label("Rooms");
//        HBox RoomsHBox = new HBox();
//        RoomsHBox.setAlignment(Pos.CENTER);
//        RoomsHBox.setSpacing(10);
////        for (Room room : UsersData.getInstance().getLoggedInUser().getRooms()){
////            Button roomButton = new Button(room.getName());
////            RoomsHBox.getChildren().add(roomButton);
////        }
//        TextField createRoomField = new TextField();
//        createRoomField.setMaxWidth(200);
//        Button createRoomButton = new Button("Create");
//        HBox createRoomHBox = new HBox(createRoomField, createRoomButton);
//        createRoomHBox.setAlignment(Pos.CENTER);
//        //vBox.getChildren().add(textField);
//        vBox.getChildren().addAll(publicChatHBox, privateChatLabel, privateChatHBox,roomLabel, RoomsHBox, createRoomHBox);
//        scrollPane.setContent(vBox);
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        Main.goToMenu("MainMenu");
    }
}
