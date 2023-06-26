package com.example.model.map;

import java.util.ArrayList;

import com.example.model.buildings.Building;
import com.example.model.people.Unit;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Tile {
    private final int rowIndex;
    private final int columnIndex;
    private final Point2D location;
    private final ArrayList<Unit> units;
    private final GraphicsContext graphicsContext;
    private Texture texture;
    private Building building;

    protected Tile(double x, double y, Texture texture, int rowIndex, int columnIndex, GraphicsContext graphicsContext) {
        this.location = new Point2D(x, y);
        this.units = new ArrayList<>();
        this.graphicsContext = graphicsContext;
        this.texture = texture;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    // TODO: need to remove when replace image done
    public void makeDiamond() {
        double x = location.getX();
        double y = location.getY();
        double TILE_LENGTH = GameMap.TILE_LENGTH;
        double[] xPoints = {x - TILE_LENGTH / 2, x, x + TILE_LENGTH / 2, x};
        double[] yPoints = {y, y + TILE_LENGTH / 2, y, y - TILE_LENGTH / 2};
        graphicsContext.fillPolygon(xPoints, yPoints, 4);
        graphicsContext.strokePolygon(xPoints, yPoints, 4);
    }

    public void clearRect() {
        graphicsContext.clearRect(location.getX() - 1, location.getY() - 1, 2, 2);
    }

    public Point2D getLocation() {
        return location;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public Texture getTexture() {
        return texture;
    }

    public Building getBuilding() {
        return building;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String showDetails() {
        // TODO
        return null;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    @Override
    public String toString() {
        return location.toString() + "\n<<<row: " + rowIndex + ">>> <<<column: " + columnIndex + ">>>";
    }

}
