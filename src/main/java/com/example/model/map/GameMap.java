package com.example.model.map;

import java.util.ArrayList;

import com.example.model.Game;
import com.example.view.images.TextureImages;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

public class GameMap extends Pane {
    public static final double TILE_LENGTH = 10.0d;
    private final Timeline timeline;
    private final DoubleProperty scale;
    private final ArrayList<Tile> centers;
    private final int length;
    private final Game game;
    private Tile selectedTile;

    public GameMap(int length, Game game) {
        this.timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        this.scale = new SimpleDoubleProperty(1.0);
        this.centers = new ArrayList<>();
        this.length = length;
        this.game = game;
        setInitScales();
        addEventFilters();
        initTiles();
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public Game getGame() {
        return game;
    }

    public Tile getSelectedTile() {
        return selectedTile;
    }

    public void setSelectedTile(double x, double y) {
        if (selectedTile != null)
            selectedTile.deselectTile();
        selectedTile = findClosestTile(x / getScale(), y / getScale());
        selectedTile.selectTile();
    }

    public Tile findClosestTile(double x, double y) {
        int yIndex = (int) Math.round(y * 2 / TILE_LENGTH);
        int xIndex = (int) Math.round((x - (yIndex % 2) * TILE_LENGTH / 2) / TILE_LENGTH);
        return getTileByIndex(xIndex, yIndex);
    }

    public Tile getTileByIndex(int xIndex, int yIndex) {
        return centers.get(yIndex * length + xIndex);
    }


    protected double getScale() {
        return scale.get();
    }

    protected void setScale(double scale) {
        this.scale.set(scale);
    }




    private void setInitScales() {
        setMaxWidth(200);
        setMaxHeight(200);
        scaleXProperty().bind(scale);
        scaleYProperty().bind(scale);
        setScale(3.0);
    }

    private void initTiles() {
        for (int yIndex = 0; yIndex < length; yIndex++)
            for (int xIndex = 0; xIndex < length; xIndex++) {
                double x = (yIndex % 2) * TILE_LENGTH / 2 + TILE_LENGTH * xIndex;
                double y = TILE_LENGTH * yIndex / 2;
                Tile tile = new Tile(x, y, TextureImages.SEA, this);
                centers.add(tile);
            }
        new Tree(this, TreeType.PINE, 100, 100);
        getTileByIndex(10, 20).selectTile();
    }

    private void addEventFilters() {
        MapGestures mapGestures = new MapGestures(this);
        addEventFilter(MouseEvent.MOUSE_PRESSED, mapGestures.getOnMousePressedEventHandler());
        addEventFilter(MouseEvent.MOUSE_DRAGGED, mapGestures.getOnMouseDraggedEventHandler());
        addEventFilter(ScrollEvent.ANY, mapGestures.getOnScrollEventHandler());
    }

    // TODO: need to remove
    public Cell getCellByLocation(int xCoordinate, int yCoordinate) {
        return null;
    }

}
