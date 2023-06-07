package com.example.view.controllers;

import com.example.controller.GameController;

import com.example.model.map.GameMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GameMenuController {
    private final GameController controller = GameController.getInstance();
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private HBox bottom;

    @FXML
    public void initialize() {
        controller.getGame().setGameMap(200);
    }
    
    
    public void initMap() {
        borderPane.setCenter(controller.getGame().getGameMap());
    }
}
