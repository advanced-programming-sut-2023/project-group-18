package com.example.view.controllers;

import com.example.model.UsersData;
import com.example.model.chat.Message;
import com.example.model.chat.PublicChat;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
//        vBox.getChildren().addAll(new Label("loooooooool"), new Label("testing something"), new Label("testing something"),
//                new Label("testing something"), new Label("testing something"), new Label("testing something"),
//                new Label("testing something"), new Label("testing something"), new Label("testing something"),
//                new Label("testing something"), new Label("testing something"), new Label("testing something"));
        for (Message message : PublicChat.getInstance().getMessages())  {
            vBox.getChildren().add(new Label(message.getText()));
        }
        borderPane.setCenter(vBox);
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        Main.goToMenu("MainMenu");
    }

    public void sendMessage(MouseEvent mouseEvent) {
        String text = chatTextField.getText();
        chatTextField.setText("");
        Label label = new Label(text);
        vBox.getChildren().add(label);
        PublicChat.getInstance().addMessage(new Message(usersData.getLoggedInUser(), text));
    }
}
