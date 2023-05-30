package com.example.model.buildings;

import com.example.model.Governance;
import com.example.model.map.Cell;
import com.example.model.people.Soldier;

import java.util.ArrayList;

public class Wall extends Tower {
    private final int height;
    private boolean hasShield;
    private final int soldiersCapacity;
    private final ArrayList<Soldier> soldiers;
    private final ArrayList<Stair> stairs;

    public Wall(BuildingType buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
        this.height = buildingType.getHeight();
        this.hasShield = false;
        this.soldiersCapacity = 5;
        this.soldiers = new ArrayList<>();
        this.stairs = new ArrayList<>();
    }

    public int getHeight() {
        return height;
    }

    public boolean hasShield() {
        return hasShield;
    }

    public int getSoldiersCapacity() {
        return soldiersCapacity;
    }

    public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }

    public void setHasShield(boolean hasShield) {
        this.hasShield = hasShield;
    }


    public boolean canMove(){
        return this.getHitpoint() == this.getBuildingType().getHitpoint();
    }
}
