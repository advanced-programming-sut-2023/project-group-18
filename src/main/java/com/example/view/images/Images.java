package com.example.view.images;

import java.net.URL;

import javafx.scene.image.Image;

public enum Images {
    ICON ("icon"),
    CURSOR ("cursor"),
    ;

    private final Image image;

    private Images(String name) {
        URL url = getClass().getResource("/images/" + name + ".png");
        image = new Image(url.toExternalForm());
    }

    public Image getImage() {
        return image;
    }

}