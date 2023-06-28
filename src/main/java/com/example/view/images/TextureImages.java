package com.example.view.images;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;

public enum TextureImages {
    FARM ("farm"),
    GROUND ("ground"),
    IRON ("iron"),
    SEA ("sea"),
    SLAB ("slab");

    private final String name;
    private final ArrayList<Image> images;

    private TextureImages(String name) {
        this.name = name;
        images = new ArrayList<>();
        int index = -1;
        while (true) {
            index++;
            URL url = getClass().getResource("/images/textures/" + name + "/" + index + ".png");
            if (url == null) return;
            Image image = new Image(url.toExternalForm());
            images.add(image);
        }
    }

    public String getName() {
        return name;
    }

    public Image getRandomImage() {
        Random random = new Random();
        return images.get(random.nextInt(images.size()));
    }

}
