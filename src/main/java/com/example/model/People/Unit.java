package com.example.model.People;

import com.example.model.Cell;
import com.example.model.Governance;
import com.example.model.Buildings.Building;

public class Unit extends Object {
    private final Governance governance;
    private final UnitType unitType;
    private Cell unitCell;
    private boolean isFree;
    private Building place;
    private int hitpoint;
    private int speed;
    private Cell targetCell;

    public Unit(Governance governance, UnitType unitType, Cell unitCell) {
        this.governance = governance;
        this.unitType = unitType;
        this.unitCell = unitCell;
        unitCell.getUnits().add(this);
        this.isFree = true;
        hitpoint = unitType.getMaxHitpoint();
        speed = unitType.getMaxSpeed();
    }

    public Governance getGovernance() {
        return governance;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public Cell getUnitCell() {
        return unitCell;
    }

    public boolean isFree() {
        return isFree;
    }

    public Building getPlace() {
        return place;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public int getSpeed() {
        return speed;
    }

    public Cell getTargetCell() {
        return targetCell;
    }

    public void setUnitCell(Cell personCell) {
        this.unitCell = personCell;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public void setPlace(Building place) {
        this.place = place;
    }

    public void setTargetCell(Cell targetCell) {
        this.targetCell = targetCell;
    }

    public boolean addHitpoint(int hitpoint) {
        this.hitpoint += hitpoint;
        this.hitpoint = unitType.getMaxHitpoint() < this.hitpoint ? unitType.getMaxHitpoint() : hitpoint;
        return hitpoint > 0;
    }

    public void reduceSpeed(int speed) {
        this.speed -= speed;
    }

    public void resetSpeed() {
        speed = unitType.getMaxSpeed();
    }

}
