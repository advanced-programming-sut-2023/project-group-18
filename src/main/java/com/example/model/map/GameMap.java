package com.example.model.map;

import java.util.ArrayList;

import com.example.model.Game;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameMap extends Pane {
    public static final double TILE_LENGTH = 10.0d;
    private final DoubleProperty scale;
    private final ArrayList<Tile> centers;
    private final int length;
    private final Game game;
    private Tile selectedTile;

    public GameMap(int length, Game game) {
        this.scale = new SimpleDoubleProperty(1.0);
        this.centers = new ArrayList<>();
        this.length = length;
        this.game = game;
        setInitScales();
        addEventFilters();
        initTiles();
    }

    public Game getGame() {
        return game;
    }

    public Tile getSelectedTile() {
        return selectedTile;
    }

    public void setSelectedType(double x, double y) {
        this.selectedTile = findClosestTile(x / getScale(), y / getScale());
        selectedTile.clearRect();
    }

    public Tile findClosestTile(double x, double y) {
        int yIndex = (int) Math.round(y * 2 / TILE_LENGTH);
        int xIndex = (int) Math.round((x - (yIndex % 2) * TILE_LENGTH / 2) / TILE_LENGTH);
        return centers.get(yIndex * length + xIndex);
    }






    private void initTiles() {
        final double width = length * TILE_LENGTH;
        final double height = length * TILE_LENGTH / 2;
        Canvas canvas = new Canvas(width, height);
        canvas.setMouseTransparent(true);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setStroke(Color.GRAY);
        graphicsContext.setLineWidth(1);
        graphicsContext.setFill(Color.BLUE);
        for (int yIndex = 0; yIndex < length; yIndex++) {
            for (int xIndex = 0; xIndex < length; xIndex++) {
                double x = (yIndex % 2) * TILE_LENGTH / 2 + TILE_LENGTH * xIndex;
                double y = TILE_LENGTH * yIndex / 2;
                Tile tile = new Tile(x, y, Texture.GROUND, yIndex, xIndex, graphicsContext);
                centers.add(tile);
                tile.makeDiamond();
            }
        }
        getChildren().add(canvas);
        canvas.toBack();
    }

    private void setInitScales() {
        setMaxWidth(200);
        setMaxHeight(200);
        scaleXProperty().bind(scale);
        scaleYProperty().bind(scale);
        setScale(3.0);
    }



    private void addEventFilters() {
        MapGestures mapGestures = new MapGestures(this);
        addEventFilter(MouseEvent.MOUSE_PRESSED, mapGestures.getOnMousePressedEventHandler());
        addEventFilter(MouseEvent.MOUSE_DRAGGED, mapGestures.getOnMouseDraggedEventHandler());
        addEventFilter(ScrollEvent.ANY, mapGestures.getOnScrollEventHandler());
    }

    protected double getScale() {
        return scale.get();
    }

    protected void setScale(double scale) {
        this.scale.set(scale);
    }

    // TODO: need to remove
    public Cell getCellByLocation(int xCoordinate, int yCoordinate) {
        return null;
    }

}
