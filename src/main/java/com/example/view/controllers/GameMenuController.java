package com.example.view.controllers;

import com.example.controller.GameController;

import com.example.model.BuildingImage;
import com.example.model.map.GameMap;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextBoundsType;

public class GameMenuController {
    private final GameController controller = GameController.getInstance();
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private VBox bottom;
    @FXML
    private HBox imagesHBox;
    @FXML
    private HBox typesHBox;
    private String[] castleBuildings = {"Armoury", "Barracks", "EngineersBuilding", "GateMain",};
    private String[] towerBuilding = {"circleTower", "drawBridge",}
    private String[] industryBuilding = {""};
    private String[] farmBuilding = {"AppleFarm"};
    private String[] foodProcessingBuilding = {""};
    private String[] townBuilding = {"Cathedral", "Church", ""};
    private String[] weaponBuilding = {"Blacksmith", "Armourer", "Fletcher"};

    public void initialize() {
        controller.getGame().setGameMap(200);
    }
    
    
    public void initMap() {
        borderPane.setCenter(controller.getGame().getGameMap());
        //bottom.setBackground(new Background(new BackgroundFill(Color.GREEN)));
        //bottom.setStyle("-fx-background-color: Green;");
        BackgroundSize backgroundSize = new BackgroundSize(1.00,
                1.00,
                true,
                true,
                false,
                false);
        BackgroundImage image = new BackgroundImage(new Image(Main.class.getResourceAsStream("/images/icons/menu.png")),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
//        Image image1 = new Image(Main.class.getResourceAsStream("/images/buildings/barracks.png"));
//        Image image2 = new Image(Main.class.getResourceAsStream("/images/buildings/mercenary post.png"));
//        ImageView imageView1 = new ImageView(image1);
//        ImageView imageView2 = new ImageView(image2);
//        imageView1.setFitHeight(80);
//        imageView1.setFitWidth(80);
//        imageView2.setFitWidth(80);
//        imageView2.setFitHeight(80);
        //imagesHBox.initStyle(StageStyle.TRANSPARENT);
//        imagesHBox.getChildren().addAll(imageView1, imageView2);
        BuildingImage buildingImage = new BuildingImage(imagesHBox, "Barracks",80);
        BuildingImage buildingImage1 = new BuildingImage(imagesHBox, "MercenaryPost",80);

        bottom.toFront();
        //bottom.setPrefHeight(300);
        //bottom.setMinHeight(600);
//        bottom.setStyle("-fx-background-color: green");
        bottom.setBackground(new Background(image));
    }
}
