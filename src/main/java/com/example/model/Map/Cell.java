package com.example.model.Map;

import java.util.ArrayList;

import com.example.model.ConsoleColors;
import com.example.model.Buildings.Building;
import com.example.model.Buildings.BuildingType;
import com.example.model.Buildings.Category;
import com.example.model.Buildings.Tree;
import com.example.model.Buildings.TreeType;
import com.example.model.Governance;
import com.example.model.People.Unit;


public class Cell implements ConsoleColors {
    private final GameMap gameMap;
    private final ArrayList<Unit> units;
    private Building building;
    private Texture texture;
    private final int xCoordinate;
    private final int yCoordinate;

    public Cell(int xCoordinate, int yCoordinate, GameMap gameMap) {
        this.gameMap = gameMap;
        units = new ArrayList<>();
        texture = Texture.getARondomTexture();
        if (texture.equals(Texture.OVERGROWN_GRASSLAND)) building = new Tree(this, TreeType.getRandomTreeType());
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
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

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public String showDetails() {
        String result = "Location: (" + xCoordinate + ", " + yCoordinate + ")";
        result += "\nTexture: " + texture.name;
        if (building != null) {
            result += "\n";
            if (!(building instanceof Tree)) result += "Building: ";
            result += building.toString();
        }
        if (!units.isEmpty()) {
            result += "\nUnits: ";
            for (Unit unit : units)
                result += "\n-" + unit.toString();
        }
        return result;
    }

    public boolean isHole(){
        return this.texture.equals(Texture.HOLE);
    }

    public double calculatePythagorean(Cell cell) {
        return Math.sqrt(Math.pow(xCoordinate - cell.xCoordinate, 2) + Math.pow(yCoordinate - cell.yCoordinate, 2));
    }

    public void addUnit(Unit unit){
        this.units.add(unit);
    }

    public String toString(int column) {
        String letter;
        if (column != 1) letter = " ";
        else if (!units.isEmpty()) letter = "S";
        else if (building == null) letter = " ";
        else if (building.getBuildingType().equals(BuildingType.TREE)) letter = "T";
        else if (building.getBuildingType().getCategory().equals(Category.TOWER)) letter = "W";
        else letter = "B";
        return texture.color + "  " + letter + "   " + RESET;
    }

    public boolean hasEnemy(Governance governance){
        for (Unit unit : this.getUnits()){
            if (!unit.getGovernance().equals(governance))
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return xCoordinate == ((Cell)obj).xCoordinate && yCoordinate == ((Cell)obj).yCoordinate;
    }

    @Override
    public String toString() {
        return "(" + xCoordinate + ", " + yCoordinate + ")";
    }

}
