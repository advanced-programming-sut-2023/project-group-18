package com.example.view.images;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;

public enum TextureImages {
    GROUND ("Ground", 0),
    FARM ("Farm", 1),
    IRON ("Iron", 2),
    WATER ("Water", 3),
    SLAB ("Slab", 4);

    private final String name;
    private final byte symbol;
    private final ArrayList<Image> images;

    private TextureImages(String name, int symbol) {
        this.name = name;
        this.symbol = (byte) symbol;
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

    public byte getSymbol() {
        return symbol;
    }

    public Image getRandomImage() {
        Random random = new Random();
        return images.get(random.nextInt(images.size()));
    }

}
