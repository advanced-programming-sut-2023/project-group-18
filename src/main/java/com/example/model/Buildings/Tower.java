package com.example.model.Buildings;

import com.example.model.People.Soldier;

import java.util.ArrayList;

public class Tower extends Building{
    private final int fireRange;
    private final int defendRange;
    private final String type;
    private int soldierNumbers;
    private boolean hasLadder;
    private final ArrayList<Soldier> soldiers;

    public Tower(String buildingType, Governance governance, Cell cell, int fireRange, int defendRange, String type) {
        super(buildingType, governance, cell);
        this.fireRange = fireRange;
        this.defendRange = defendRange;
        this.type = type;
        hasLadder = false;
        soldierNumbers = 0;
        soldiers = new ArrayList<>();
    }

    public int getFireRange() {
        return fireRange;
    }

    public int getDefendRange() {
        return defendRange;
    }

    public String getType() {
        return type;
    }

    public int getSoldierNumbers() {
        return soldierNumbers;
    }

    public boolean isHasLadder() {
        return hasLadder;
    }

    public void setLadder() {
        this.hasLadder = true;
    }

    public void removeLadder(){
        this.hasLadder = false;
    }

    public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }

    public void addSoldier(Soldier soldier){
        this.soldiers.add(soldier);
    }
}
