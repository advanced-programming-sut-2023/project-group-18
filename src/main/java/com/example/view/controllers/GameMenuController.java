package com.example.view.controllers;

import com.example.controller.GameController;

import com.example.model.BuildingImage;
import com.example.model.buildings.BarCategory;
import com.example.model.buildings.BuildingType;
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

import java.util.ArrayList;
import java.util.List;

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
    public static List<List<BuildingImage>> listOfLists = new ArrayList<List<BuildingImage>>(7);
    public static BarCategory currentCategory = BarCategory.CASTLE;
    private String[] castleBuildings = {"Armoury", "Barracks", "EngineersBuilding", "GateMain",};
    private String[] towerBuilding = {"circleTower", "drawBridge",};
    private String[] industryBuilding = {""};
    private String[] farmBuilding = {"AppleFarm"};
    private String[] foodProcessingBuilding = {""};
    private String[] townBuilding = {"Cathedral", "Church", ""};
    private String[] weaponBuilding = {"Blacksmith", "Armourer", "Fletcher"};

    public void initialize() {
        controller.getGame().setGameMap(100);
        for (BarCategory barCategory : BarCategory.values()){
            if (!barCategory.equals(BarCategory.NONE)){
                ArrayList<BuildingImage> buildingImages = new ArrayList<>();
                for (BuildingType buildingType : BuildingType.values()){
                    if (buildingType.getBarCategory().equals(barCategory)){
                        try {
                            buildingImages.add(new BuildingImage(imagesHBox, buildingType.getName(), 80, this));
                        }
                        catch (Exception e){
                            System.out.println(buildingType.getName());
                            System.out.println(e.getMessage());
                        }
                    }
                }
                listOfLists.add(buildingImages);
            }
        }
        for (List<BuildingImage> buildingImages : listOfLists){
            if (buildingImages.size() > 6){
                for (BuildingImage buildingImage : buildingImages){
                    buildingImage.setSize(45);
                }
            }
        }
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
//        for (BuildingType buildingType : BuildingType.values()){
//            if (buildingType.getBarCategory().equals(BarCategory.CASTLE)){
//
//            }
//        }
        ArrayList<BuildingImage> typeList = new ArrayList<>();
        for (BarCategory barCategory : BarCategory.values()){
            if (!barCategory.equals(BarCategory.NONE))
                typeList.add(new BuildingImage(typesHBox, barCategory.getName(), 20, this));
        }
        changeMenu(currentCategory);
//        typeList.add(new BuildingImage(typesHBox, "CastleBuilding", 20));
//        typeList.add(new BuildingImage(typesHBox, "FarmBuilding", 20));
//        typeList.add(new BuildingImage(typesHBox, "FoodProcessing", 20));
//        typeList.add(new BuildingImage(typesHBox, "IndustryBuilding", 20));
//        typeList.add(new BuildingImage(typesHBox, "TowerBuilding", 20));
//        typeList.add(new BuildingImage(typesHBox, "TowerBuilding", 20));
//        typeList.add(new BuildingImage(typesHBox, "WeaponBuilding", 20));
//        for (BuildingType buildingType : BuildingType.values()){
//            if (buildingType.getBarCategory().equals(currentCategory)){
//                try {
//                    castleImages.add(new BuildingImage(imagesHBox, buildingType.getName(), 80));
//                }
//                catch (Exception e){
//                    System.out.println(buildingType.getName());
//                    System.out.println(e.getMessage());
//                }
//            }
//        }
        //BuildingImage buildingImage = new BuildingImage(imagesHBox, "Barracks",80);
        //BuildingImage buildingImage1 = new BuildingImage(imagesHBox, "mercenary post",80);

        bottom.toFront();
        //bottom.setPrefHeight(300);
        //bottom.setMinHeight(600);
//        bottom.setStyle("-fx-background-color: green");
        bottom.setBackground(new Background(image));
    }
    public void changeMenu(BarCategory barCategory){
        for (List<BuildingImage> buildingImages : listOfLists){
            if (BuildingType.getBuildingTypeByName(
                    buildingImages.get(0).getName()).getBarCategory().equals(barCategory)){
                imagesHBox.getChildren().removeAll(imagesHBox.getChildren());
                for (BuildingImage buildingImage : buildingImages){
                    imagesHBox.getChildren().add(buildingImage.getImageView());
                }
            }
        }
    }
}
