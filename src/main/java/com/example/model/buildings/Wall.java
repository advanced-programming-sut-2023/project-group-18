package com.example.model.buildings;

import com.example.model.Governance;
import com.example.model.map.Tile;

public class Wall extends Tower {
    private final int height;
    private boolean hasShield;

    public Wall(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
        this.height = buildingType.getHeight();
        this.hasShield = false;
    }

    public int getHeight() {
        return height;
    }

    public boolean hasShield() {
        return hasShield;
    }

    public void setHasShield(boolean hasShield) {
        this.hasShield = hasShield;
    }

    public boolean canMove(){
        return this.getHitpoint() == this.getBuildingType().getHitpoint();
    }

}
