package com.example.model;

import com.example.view.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class BuildingImage {
    private HBox imageHBox;
    private String name;
    private int size;

    public BuildingImage(HBox imageHBox, String name, int size) {
        this.imageHBox = imageHBox;
        this.name = name;
        this.size = size;
        Image image = new Image(Main.class.getResourceAsStream("/images/buildings/" + name + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);
        imageHBox.getChildren().add(imageView);
    }


}
