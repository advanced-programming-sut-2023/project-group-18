package com.example.model.map;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class MapGestures {
    private static final double MAX_SCALE = 5.0d;
    private static final double MIN_SCALE = 2.0d;
    private static final double DELTA_SCALE = 1.05d;
    private GameMap gameMap;
    private double mouseAnchorX;
    private double mouseAnchorY;
    private double translateAnchorX;
    private double translateAnchorY;

    protected MapGestures(GameMap gameMap) {
        this.gameMap = gameMap;
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
            gameMap.setSelectedType(new Point2D(mouseAnchorX + translateAnchorX, mouseAnchorY + translateAnchorY));
        }

    };

    private EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (!event.isSecondaryButtonDown())
                return;
            final double xTranslate = translateAnchorX + event.getSceneX() - mouseAnchorX;
            final double yTranslate = translateAnchorY + event.getSceneY() - mouseAnchorY;
            gameMap.setTranslateX(xTranslate);
            gameMap.setTranslateY(yTranslate);
            event.consume();
        }
    };

    private EventHandler<ScrollEvent> onScrollEventHandler = new EventHandler<ScrollEvent>() {
        @Override
        public void handle(ScrollEvent event) {
            double scale = gameMap.getScale();
            final double oldScale = scale;
            if (event.getDeltaY() < 0)
                scale /= DELTA_SCALE;
            else
                scale *= DELTA_SCALE;
            scale = clamp(scale, MIN_SCALE, MAX_SCALE);
            final double f = (scale / oldScale) - 1;
            final double dx = (event.getX() - (gameMap.getBoundsInParent().getWidth() / 2 + gameMap.getBoundsInParent().getMinX()));
            final double dy = (event.getY() - (gameMap.getBoundsInParent().getHeight() / 2 + gameMap.getBoundsInParent().getMinY()));
            gameMap.setScale(scale);
            gameMap.setPivot(f*dx, f*dy);
            event.consume();
        }

    };


}
