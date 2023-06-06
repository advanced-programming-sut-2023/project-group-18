package com.example.model.map;

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
    private final DoubleProperty scale;
    private final int length;
    private final Game game;

    public GameMap(int length, Game game) {
        this.scale = new SimpleDoubleProperty(1.0);
        this.length = length;
        this.game = game;
        setInitScales();
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

    private void addGrid() {
        final double width = getBoundsInLocal().getWidth();
        final double height = getBoundsInLocal().getHeight();
        Canvas grid = new Canvas(width, height);
        grid.setMouseTransparent(true);
        GraphicsContext graphicsContext = grid.getGraphicsContext2D();
        // graphicsContext.drawImage(null, h, h, w, h);
        graphicsContext.setStroke(Color.GRAY);
        graphicsContext.setLineWidth(1);
        final double offset = 50;
        for(double index = offset; index < width; index += offset) {
            graphicsContext.strokeLine(index, 0, index, height);
            graphicsContext.strokeLine(0, index, width, index);
        }
        getChildren().add(grid);
        grid.toBack();
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
