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
    private static final double TILE_LENGTH = 50.0d;
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
        initTiles();
    }

    public Game getGame() {
        return game;
    }

    public Tile getSelectedTile() {
        return selectedTile;
    }

    public void setSelectedType(Point2D location) {
        this.selectedTile = findClosestTile(location);
        System.out.println(selectedTile.getLocation());
    }

    public Tile findClosestTile(Point2D location) {
        return findClosestTile(location.getX(), location.getY());
    }

    private Tile getTileByIndex(int xIndex, int yIndex) {
        return centers.get(yIndex * length + xIndex);
    }

    public Tile findClosestTile(double x, double y) {
        int yIndex = (int) Math.round(y * 2 / TILE_LENGTH);
        int xIndex = (int) Math.round((x - (yIndex % 2) * TILE_LENGTH / 2) / TILE_LENGTH);
        return getTileByIndex(xIndex, yIndex);
    }






    private void initTiles() {
        final double width = 200;
        final double height = 200;
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
                centers.add(new Tile(x, y, Texture.GROUND));
                makeDiamond(graphicsContext, x, y); // TODO: have to replace it with image
            }
        }
        getChildren().add(canvas);
        canvas.toBack();
    }

    private void setInitScales() {
        scaleXProperty().bind(scale);
        scaleYProperty().bind(scale);
        setScale(3.0);
        setTranslateX(100);
        setTranslateY(100);
        addEventFilters();
    }

    // TODO: need to remove when replace image done
    private void makeDiamond(GraphicsContext graphicsContext, double x, double y) {
        double[] xPoints = {x - TILE_LENGTH, x, x + TILE_LENGTH, x};
        double[] yPoints = {y, y + TILE_LENGTH / 2, y, y - TILE_LENGTH / 2};
        graphicsContext.fillPolygon(xPoints, yPoints, 4);
        graphicsContext.strokePolygon(xPoints, yPoints, 4);
//        graphicsContext.rect(x, y, TILE_LENGTH, TILE_LENGTH);
//        graphicsContext.strokeLine(100, 100, x, y);
        
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

    protected void setPivot(double x, double y) {
        setTranslateX(getTranslateX() - x);
        setTranslateY(getTranslateY() - y);
    }

    // TODO: need to remove
    public Cell getCellByLocation(int xCoordinate, int yCoordinate) {
        return null;
    }

}
