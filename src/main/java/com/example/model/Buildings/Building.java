package com.example.model.Buildings;

import java.util.HashMap;

import com.example.model.Cell;
import com.example.model.Governance;
import com.example.model.Assets.Asset;

public class Building {
    private final String buildingType;
    protected final int workersNumber;
    protected final Governance governance;
    protected int hitpoint;
    protected final String groundType;
    protected final Asset resourceType;
    protected final int resourceCost;
    protected final Cell cell;

    public Building(String buildingType, Governance governance, Cell cell) {
        this.buildingType = buildingType;
        this.workersNumber = workersNumber;
        this.governance = governance;
        this.hitpoint = hitpoint;
        this.groundType = groundType;
        this.costHashmap = costHashmap;
        this.cell = cell;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public int getWorkersNumber() {
        return workersNumber;
    }

    public Governance getGovernance() {
        return governance;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public String getGroundType() {
        return groundType;
    }

    public HashMap<String, Integer> getCostHashmap() {
        return costHashmap;
    }

    public Cell getCell() {
        return cell;
    }
}
