package com.example.client.model;

import com.example.client.model.assets.Asset;
import com.example.client.model.assets.AssetType;
import com.example.client.model.buildings.BarCategory;
import com.example.client.model.buildings.BuildingType;
import com.example.client.view.Client;
import com.example.client.view.controllers.GameMenuController;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class BuildingImage {
    private HBox imageHBox;
    private String name;
    private int size;
    private ImageView imageView;
    private GameMenuController gameMenuController;
    private String where;

    public BuildingImage(HBox imageHBox, String name, int size, GameMenuController gameMenuController, boolean type, String where) {
        this.imageHBox = imageHBox;
        this.name = name;
        this.size = size;
        this.gameMenuController = gameMenuController;
        this.where = where;
        Image image = new Image(Client.class.getResourceAsStream("/images/" + where + "/" + name + ".png"));
        imageView = new ImageView(image);
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);
        if (type && where.equals("buildings")) {
            imageHBox.getChildren().add(imageView);
//            TODO: OMID
//            imageView.setOnMouseClicked(mouseEvent -> gameMenuController.changeMenu(BarCategory.getBarCategoryByName(name)));
        } else if (!type && where.equals("buildings")) {
//            TODO: OMID
//            imageView.setOnMouseClicked(mouseEvent -> gameMenuController.setSelectedBuilding(BuildingType.getBuildingTypeByName(name)));
        } else if (type && where.equals("assets")) {
            imageView.setOnMouseClicked(mouseEvent -> {
                gameMenuController.cleanTypesHBox();
//                TODO: OMID
//                gameMenuController.changeShopAssetType(AssetType.getAssetTypeByName(name));
            });
        } else {
//            TODO: OMID
//            imageView.setOnMouseClicked(mouseEvent -> gameMenuController.clickOnAsset(Asset.getAssetByName(name)));
        }
        //imageHBox.getChildren().add(imageView);
    }

    public void addToHBox() {
        imageHBox.getChildren().add(imageView);
    }

    public void removeOfHBox() {
        imageHBox.getChildren().remove(imageView);
    }

    public HBox getImageHBox() {
        return imageHBox;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setSize(int size) {
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);
    }
}
