package com.example.model;

import java.util.ArrayList;

import com.example.model.Buildings.Building;
import com.example.model.People.Unit;

public class Cell implements ConsoleColors {
    private final ArrayList<Unit> units;
    private Building building;
    private Texture texture;
    private final int xCordinate;
    private final int yCordinate;

    protected Cell(int xCordinate, int yCordinate) {
        units = new ArrayList<>();
        texture = Texture.getARondomTexture();
        this.xCordinate = xCordinate;
        this.yCordinate = yCordinate;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public Building getBuilding() {
        return building;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getxCordinate() {
        return xCordinate;
    }

    public int getyCordinate() {
        return yCordinate;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public String showDetails() {
        // TODO: have to complete
        return "Texture: " + texture.name
            + "\n"
                ;
    }

    @Override
    public String toString() {
        return texture.color + "      " + RESET;
    }

}
