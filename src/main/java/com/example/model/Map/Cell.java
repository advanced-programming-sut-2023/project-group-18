package com.example.model.Map;

import java.util.ArrayList;

import com.example.model.ConsoleColors;
import com.example.model.Buildings.Building;
import com.example.model.People.Unit;

public class Cell implements ConsoleColors {
    private final GameMap gameMap;
    private final ArrayList<Unit> units;
    private Building building;
    private Texture texture;
    private final int xCordinate;
    private final int yCordinate;

    public Cell(int xCordinate, int yCordinate, GameMap gameMap) {
        this.gameMap = gameMap;
        units = new ArrayList<>();
        texture = Texture.getARondomTexture();
        this.xCordinate = xCordinate;
        this.yCordinate = yCordinate;
    }

    public GameMap getGameMap() {
        return gameMap;
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

    public boolean isHole(){
        return this.texture.equals(Texture.HOLE);
    }
    public double calculatePythagorean(Cell cell) {
        return Math.sqrt(Math.pow(xCordinate, cell.xCordinate) + Math.pow(yCordinate, cell.yCordinate));
    }

    public void addUnit(Unit unit){
        this.units.add(unit);
    }

    @Override
    public String toString() {
        return texture.color + "      " + RESET;
    }

}
