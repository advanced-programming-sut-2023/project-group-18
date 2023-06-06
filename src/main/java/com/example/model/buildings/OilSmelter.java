package com.example.model.buildings;

import com.example.model.Governance;
import com.example.model.map.Cell;
import com.example.model.people.UnitType;

public class OilSmelter extends IndustrialBuilding{
    private int oilCount;
    public OilSmelter(BuildingType buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
        this.oilCount = 0;
    }
    public boolean canWork(){
        return workers.get(0).getUnitType().equals(UnitType.ENGINEER) && super.canWork() && this.oilCount != 0;
    }

    public void produce(){
        this.oilCount += this.getBuildingType().getProductionRate();
    }

    public void useOil(){
        this.oilCount --;
    }
}