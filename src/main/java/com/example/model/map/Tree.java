package com.example.model.map;

import javafx.animation.KeyFrame;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Tree {
    private static final int FRAME = 200;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    private final GameMap gameMap;
    private final TreeType treeType;
    private final ImageView imageView;
    private final KeyFrame keyFrame;
    private int index;

    public Tree(GameMap gameMap, TreeType treeType, double x, double y) {
        this.gameMap = gameMap;
        this.treeType = treeType;
        this.imageView = setSize();
        this.keyFrame = addKeyFrame();
        addToMap(x, y);
    }

    private ImageView setSize() {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);
        return imageView;
    }

    private KeyFrame addKeyFrame() {
        return new KeyFrame(Duration.millis(FRAME), event -> {
            imageView.setImage(treeType.getImages()[index]);
            index = (index + 1) % treeType.getImages().length;
        });
    }

    private void addToMap(double x, double y) {
        gameMap.getChildren().add(imageView);
        imageView.setLayoutX(x - treeType.getxReset());
        imageView.setLayoutY(y - treeType.getyReset());
        gameMap.getTimeline().getKeyFrames().add(keyFrame);
        gameMap.getTimeline().play();
    }

    public void removeFromMap() {
        gameMap.getChildren().remove(imageView);
        gameMap.getTimeline().getKeyFrames().remove(keyFrame);
    }

    @Override
    public String toString() {
        return treeType.getName();
    }

}
