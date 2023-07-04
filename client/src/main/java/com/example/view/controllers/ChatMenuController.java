package com.example.view.controllers;

import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
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
        vBox.getChildren().add(label);
        vBox.getChildren().add(backButton);
        vBox.setAlignment(Pos.CENTER);
        scrollPane.setContent(vBox);
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        Main.goToMenu("MainMenu");
    }
}
