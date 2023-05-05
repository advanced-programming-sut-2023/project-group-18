package com.example.model.Buildings;

import com.example.model.Cell;
import com.example.model.Governance;
import com.example.model.People.Soldier;

import java.util.ArrayList;

public class Wall extends Building{
    private final int height;
    private boolean hasShield;
    private final int soldiersCapacity;
    private final ArrayList<Soldier> soldiers;
    private final boolean hasLadder;

    public Wall(BuildingType buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
        this.height = buildingType.getHeight();
        this.hasShield = false;
        this.soldiersCapacity = 5;
        this.soldiers = new ArrayList<>();
        this.hasLadder = false;
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

    public boolean hasLadder() {
        return hasLadder;
    }

    public boolean addLadder(){

    }

    public boolean canMove(){
        return this.getHitpoint() == this.getBuildingType().getHitpoint();
    }
}
