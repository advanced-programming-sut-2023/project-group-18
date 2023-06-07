package com.example.model.map;

import java.util.ArrayList;

import com.example.model.Game;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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

    public GameMap(int length, Game game) {
        this.scale = new SimpleDoubleProperty(1.0);
        this.centers = new ArrayList<>();
        this.length = length;
        this.game = game;
        setInitScales();
        initTiles();
    }















    private void initTiles() {
        final double width = getBoundsInLocal().getWidth();
        final double height = getBoundsInLocal().getHeight();
        Canvas grid = new Canvas(width, height);
        grid.setMouseTransparent(true);
        GraphicsContext graphicsContext = grid.getGraphicsContext2D();
        graphicsContext.setStroke(Color.GRAY);
        graphicsContext.setLineWidth(1);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                double x = (i % 2) * TILE_LENGTH / 2 + TILE_LENGTH * j;
                double y = TILE_LENGTH * i / 2;
                centers.add(new Tile(x, y, Texture.GROUND));
                makeDiamond(graphicsContext, x, y);
            }
        }
        getChildren().add(grid);
        grid.toBack();
    }

    private void setInitScales() {
        // setPrefSize(600, 600);
        // setStyle("-fx-background-color: lightgrey; -fx-border-color: blue;");
        scaleXProperty().bind(scale);
        scaleYProperty().bind(scale);
        setScale(3.0);
        setTranslateX(100);
        setTranslateY(100);
        addGrid();
        addEventFilters();
    }

    private void makeDiamond(GraphicsContext graphicsContext, double x, double y) {
        double[] xPoints = {x - TILE_LENGTH, x, x + TILE_LENGTH, x};
        double[] yPoints = {y, y + TILE_LENGTH / 2, y, y - TILE_LENGTH / 2};
        graphicsContext.fillPolygon(xPoints, yPoints, 4);
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

}
