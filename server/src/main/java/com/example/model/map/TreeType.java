package com.example.model.map;

import java.net.URL;
import java.util.Random;

import javafx.scene.image.Image;

public enum TreeType {
    BIRCH ("Birch", 1, 9.5, 24),
    CHESTNUT ("Chestnut", 2, 12.5, 22),
    OAK ("Oak", 3, 9.5, 26.5),
    PINE ("Pine",4, 10, 29);

    private final String name;
    private final byte symbol;
    private final double xReset;
    private final double yReset;
    private final Image[] images;

    private TreeType(String name, int symbol, double xReset, double yReset) {
        this.name = name;
        this.symbol = (byte) symbol;
        this.xReset = xReset;
        this.yReset = yReset;
        this.images = new Image[4];
        uploadImages();
    }

    public static TreeType getARandomTreeType() {
        Random random = new Random();
        return TreeType.values()[random.nextInt(TreeType.values().length)];
    }

    public static TreeType getTreeTypeBySymbol(int symbol) {
        if (symbol == 0) return null;
        return TreeType.values()[symbol - 1];
    }

    public String getName() {
        return name;
    }

    public byte getSymbol() {
        return symbol;
    }

    public Image[] getImages() {
        return images;
    }

    public double getxReset() {
        return xReset;
    }

    public double getyReset() {
        return yReset;
    }

    private void uploadImages() {
        int index = 0;
        while (index < 4) {
            images[index] = getImage(index + 1);
            index++;
        }
    }

    private Image getImage(int index) {
        URL url = getClass().getResource("/images/trees/" + name + "/" + index + ".png");
        return new Image(url.toExternalForm());
    }

}
