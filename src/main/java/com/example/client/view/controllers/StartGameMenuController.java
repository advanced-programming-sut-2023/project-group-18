package com.example.client.view.controllers;

import com.example.client.view.Client;
import com.example.server.controller.StartGameMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class StartGameMenuController {
    private final StartGameMethods startGameMethods = StartGameMethods.getInstance();
    @FXML
    private Label usernameError;
    @FXML
    private HBox hBox;
    @FXML
    private Button submit;
    @FXML
    private BorderPane borderPane;
    @FXML
    private ChoiceBox startGameChoiceBox;
    private final ArrayList<TextField> usernames = new ArrayList<>();

    public void usernameTextFields(ActionEvent actionEvent) {
        System.out.println("username textfields is called");
        String numberString = (String) startGameChoiceBox.getSelectionModel().getSelectedItem();
        int number = Integer.parseInt(numberString);
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        for (int i = 0; i < number - 1; i++) {
            System.out.println("the  number" + vBox.getChildren().size());
            TextField textField = new TextField();
            textField.setMaxWidth(200);
            textField.setPromptText("user " + (i + 1));
            usernames.add(textField);
            vBox.getChildren().add(textField);
        }
        submit.setVisible(true);
//        submit.setManaged(true);
//        for (TextField textField : usernames) {
//            vBox.getChildren().add(textField);
//            borderPane.getChildren().add(textField);
//        }
        vBox.setAlignment(Pos.CENTER_RIGHT);
        if (hBox.getChildren().size() > 2)
            hBox.getChildren().remove(hBox.getChildren().get(hBox.getChildren().size() - 1));
        hBox.getChildren().add(vBox);
//        borderPane.setBottom(vBox);
//        borderPane.getChildren().add(new Label("testing my sanity"));
    }

    public void submit(MouseEvent mouseEvent) throws IOException {
        if (!startGameMethods.checkStartPlayers(usernames))
            usernameError.setText("not all usernames exist!");
        else {
            Client.goToMenu("GameMenu");
        }
    }
}