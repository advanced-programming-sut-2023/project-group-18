package com.example.model.buildings;

import com.example.model.Governance;
import com.example.model.map.Tile;

import java.util.ArrayList;

public class Wall extends Tower {
    private final int height;
    private boolean hasShield;
    private final int soldiersCapacity;
    private final ArrayList<Stair> stairs;

    public Wall(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
        this.height = buildingType.getHeight();
        this.hasShield = false;
        this.soldiersCapacity = 5;
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

    public void setHasShield(boolean hasShield) {
        this.hasShield = hasShield;
    }


    public boolean canMove(){
        return this.getHitpoint() == this.getBuildingType().getHitpoint();
    }
}
