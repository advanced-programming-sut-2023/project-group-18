package com.example.model.map;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Rotate;

public class MapGestures {
    private static final double MAX_SCALE = 15.0d;
    private static final double MIN_SCALE = 3.0d;
    private static final double DELTA_SCALE = 1.05d;
    protected static final double RESET_X = 420.0d;
    protected static final double RESET_Y = 59.0d;
    private static final double DEGREE = 60.0;
    private static final double DEGREE_RADIANS = DEGREE * Math.PI / 180;
    private GameMap gameMap;
    private double mouseAnchorX;
    private double mouseAnchorY;
    private double translateAnchorX;
    private double translateAnchorY;

    protected MapGestures(GameMap gameMap) {
        this.gameMap = gameMap;
        gameMap.setTranslateX(-RESET_X);
        gameMap.setTranslateY(-RESET_Y);
        Rotate rotate = new Rotate(DEGREE, Rotate.X_AXIS);
        gameMap.getTransforms().add(rotate);
    }

    private double getResetX() {
        return gameMap.getBoundsInParent().getMinX();
    }

    private double getResetY() {
        return gameMap.getBoundsInParent().getMinY();
    }

    private double clamp(double value, double min, double max) {
        if (Double.compare(value, min) < 0)
            return min;
        if (Double.compare(value, max) > 0)
            return max;
        return value;
    }

    protected EventHandler<MouseEvent> getOnMousePressedEventHandler() {
        return onMousePressedEventHandler;
    }

    protected EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
        return onMouseDraggedEventHandler;
    }

    protected EventHandler<MouseEvent> getOnMouseMovedEventHandler() {
        return onMouseMovedEventHandler;
    }

    protected EventHandler<ScrollEvent> getOnScrollEventHandler() {
        return onScrollEventHandler;
    }

    private EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            mouseAnchorX = event.getSceneX();
            mouseAnchorY = event.getSceneY();
            translateAnchorX = gameMap.getTranslateX();
            translateAnchorY = gameMap.getTranslateY();
            if (gameMap.getSelectedBuilding() != null && !event.isSecondaryButtonDown()) {
                System.out.println(gameMap.getSelectedBuilding().getName());
                double x = (mouseAnchorX - getResetX()) / gameMap.getScale() - GameMap.TILE_LENGTH / 2;
                double y = ((mouseAnchorY - getResetY()) / gameMap.getScale() - GameMap.TILE_LENGTH / 2) / Math.cos(DEGREE_RADIANS);
                gameMap.dropBuilding(x, y);
                gameMap.setSelectedBuilding(null);
            }
        }
    };

    private EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (!event.isSecondaryButtonDown())
                return;
            final double xTranslate = translateAnchorX + event.getSceneX() - mouseAnchorX;
            final double yTranslate = translateAnchorY + event.getSceneY() - mouseAnchorY;
            // if (xTranslate > -RESET_X|| yTranslate > -RESET_Y) return; // TODO drag down
            gameMap.setTranslateX(xTranslate);
            gameMap.setTranslateY(yTranslate);
            event.consume();
        }
    };

    private EventHandler<MouseEvent> onMouseMovedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getButton().compareTo(MouseButton.PRIMARY) != 0)
                return;
                double mouseAnchorX2 = event.getSceneX();
                double mouseAnchorY2 = event.getSceneY();
                double startX = (mouseAnchorX - getResetX()) / gameMap.getScale() - GameMap.TILE_LENGTH / 2;
                double startY = ((mouseAnchorY - getResetY()) / gameMap.getScale() - GameMap.TILE_LENGTH / 2) / Math.cos(DEGREE_RADIANS);
                double endX = (mouseAnchorX2 - getResetX()) / gameMap.getScale() - GameMap.TILE_LENGTH / 2;
                double endY = ((mouseAnchorY2 - getResetY()) / gameMap.getScale() - GameMap.TILE_LENGTH / 2) / Math.cos(DEGREE_RADIANS);
                gameMap.setSelectedTiles(startX, startY, endX, endY);
            }
    };

    private EventHandler<ScrollEvent> onScrollEventHandler = new EventHandler<ScrollEvent>() {
        @Override
        public void handle(ScrollEvent event) {
            double scale = gameMap.getScale();
            if (event.getDeltaY() < 0)
                scale /= DELTA_SCALE;
            else
                scale *= DELTA_SCALE;
            scale = clamp(scale, MIN_SCALE, MAX_SCALE);
            gameMap.setScale(scale);
            event.consume();
        }

    };


}
