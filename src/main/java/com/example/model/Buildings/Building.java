package com.example.model.Buildings;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.model.Cell;
import com.example.model.Governance;
import com.example.model.Assets.Asset;
import com.example.model.People.Unit;
import com.example.model.People.UnitType;
import com.example.model.People.Worker;
import com.example.model.Map.Texture;

public class Building {
    private final BuildingType buildingType;
    protected final ArrayList<Unit> workers;
    protected final Governance governance;
    protected final Texture groundType;
    protected final int goldCost;
    protected final Asset resourceType;
    protected final int resourceCost;
    protected final Cell cell;
    protected int hitpoint;
    protected final int width;

    public Building(BuildingType buildingType, Governance governance, Cell cell) {
        this.buildingType = buildingType;
        this.workers = new ArrayList<>();
        this.governance = governance;
        this.hitpoint = buildingType.getHitpoint();
        this.groundType = buildingType.getGroundType();
        this.goldCost = buildingType.getGoldCost();
        this.resourceType = buildingType.getResourceType();
        this.resourceCost = buildingType.getResourceCost();
        this.cell = cell;
        this.width = buildingType.getWidth();
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }


    public Governance getGovernance() {
        return governance;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public Texture getGroundType() {
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

    public ArrayList<Unit> getWorkers() {
        return workers;
    }

    public int getWorkersNumber(){
        return this.workers.size();
    }
    public void addWorker(){
        workers.add(new Worker(governance,UnitType.getUnitTypeByBuildingType(buildingType),cell));
    }

    public void updateWorkers(){
        for (Unit unit : this.workers){
            if (unit.getHitpoint() == 0)
                workers.remove(unit);
        }
    }

    public boolean canWork(){
        return this.getWorkersNumber() == this.getBuildingType().getWorkersNumber();
    }

    public void doDamage(int damage){
        if (this.getHitpoint() <= damage)
            return;
        this.hitpoint -= damage;
    }
}
