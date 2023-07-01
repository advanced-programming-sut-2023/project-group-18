package com.example.client.model.map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Tree {
    private static final int FRAME = 250;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    private final GameMap gameMap;
    private final TreeType treeType;
    private final ImageView imageView;
    private final Point2D point2d;
    private final Timeline timeline;
    private int index;

    private Tree(GameMap gameMap, TreeType treeType, double x, double y) {
        this.gameMap = gameMap;
        this.treeType = treeType;
        this.imageView = setSize();
        this.timeline = new Timeline();
        addKeyFrame();
        this.point2d = new Point2D(x, y);
    }

    public static void addTree(GameMap gameMap, int symbol, double x, double y) {
        TreeType treeType = TreeType.getTreeTypeBySymbol(symbol);
        if (treeType == null) return;
        new Tree(gameMap, treeType, x, y).addToMap();
    }

    public static void addTree(GameMap gameMap, Tile tile) {
        new Tree(gameMap, TreeType.getARandomTreeType(), tile.getPoint2d().getX(), tile.getPoint2d().getY()).addToMap();
    }

    private ImageView setSize() {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);
        return imageView;
    }

    private void addKeyFrame() {
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(FRAME), event -> {
            imageView.setImage(treeType.getImages()[index]);
            index = (index + 1) % treeType.getImages().length;
        }));
        timeline.setCycleCount(-1);
        timeline.play();
    }

    private void addToMap() {
        gameMap.findClosestTile(point2d.getX(), point2d.getY()).setTree(this);
        gameMap.getChildren().add(imageView);
        imageView.setLayoutX(point2d.getX() - treeType.getxReset());
        imageView.setLayoutY(point2d.getY() - treeType.getyReset());
    }

    public Point2D getPoint2d() {
        return point2d;
    }

    public TreeType getTreeType() {
        return treeType;
    }

    public void removeFromMap() {
        gameMap.findClosestTile(point2d.getX(), point2d.getY()).setTree(null);
        timeline.setCycleCount(0);
        timeline.play();
        
        gameMap.getChildren().remove(imageView);
    }

    @Override
    public String toString() {
        return treeType.getName();
    }

}
