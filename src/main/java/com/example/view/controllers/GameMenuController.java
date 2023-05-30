package com.example.view.controllers;

import com.example.controller.GameController;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class GameMenuController {
    private final GameController controller = GameController.getInstance();

    @FXML
    private BorderPane borderPane;
    @FXML
    private HBox bottom;

    @FXML
    public void initialize() {
        borderPane.setCenter(controller.getCanvas());
    }
    

}
