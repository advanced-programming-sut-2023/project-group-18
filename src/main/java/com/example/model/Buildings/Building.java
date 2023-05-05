package com.example.model.Buildings;

import java.util.HashMap;

import com.example.model.Cell;
import com.example.model.Governance;
import com.example.model.Assets.Asset;

public class Building {
    private final BuildingType buildingType;
    protected final int workersNumber;
    protected final Governance governance;
    protected final String groundType;
    protected final int goldCost;
    protected final Asset resourceType;
    protected final int resourceCost;
    protected final Cell cell;
    protected int hitpoint;

    public Building(BuildingType buildingType, Governance governance, Cell cell) {
        this.buildingType = buildingType;
        this.workersNumber = buildingType.getWorkersNumber();
        this.governance = governance;
        this.hitpoint = buildingType.getHitpoint();
        this.groundType = buildingType.getGroundType();
        this.goldCost = buildingType.getGoldCost();
        this.resourceType = buildingType.getResourceType();
        this.resourceCost = buildingType.getResourceCost();
        this.cell = cell;
    }

    public BuildingType getBuildingType() {
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

    public Cell getCell() {
        return cell;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public Asset getResourceType() {
        return resourceType;
    }

    public int getResourceCost() {
        return resourceCost;
    }
}
