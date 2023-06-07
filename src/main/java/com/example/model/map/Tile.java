package com.example.model.map;

import java.util.ArrayList;

import com.example.model.buildings.Building;
import com.example.model.people.Unit;

import javafx.geometry.Point2D;

public class Tile {
    private final Point2D location;
    private final ArrayList<Unit> units;
    private Texture texture;
    private Building building;

    protected Tile(double x, double y, Texture texture) {
        this.location = new Point2D(x, y);
        this.units = new ArrayList<>();
        this.texture = texture;
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

}
