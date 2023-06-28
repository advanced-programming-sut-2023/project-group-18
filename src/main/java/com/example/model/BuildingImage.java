package com.example.model;

import com.example.model.buildings.BarCategory;
import com.example.view.Main;
import com.example.view.controllers.GameMenuController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.event.EventHandler;
public class BuildingImage {
    private HBox imageHBox;
    private String name;
    private int size;
    private ImageView imageView;
    private GameMenuController gameMenuController;

    public BuildingImage(HBox imageHBox, String name, int size, GameMenuController gameMenuController) {
        this.imageHBox = imageHBox;
        this.name = name;
        this.size = size;
        this.gameMenuController = gameMenuController;
        Image image = new Image(Main.class.getResourceAsStream("/images/buildings/" + name + ".png"));
        imageView = new ImageView(image);
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);
        if (size == 20){
            imageHBox.getChildren().add(imageView);
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    gameMenuController.changeMenu(BarCategory.getBarCategoryByName(name));
                }
            });
        }
        //imageHBox.getChildren().add(imageView);
    }

    public void addToHBox(){
        imageHBox.getChildren().add(imageView);
    }

    public void removeOfHBox(){
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

    public void setSize(int size){
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);
    }
}
