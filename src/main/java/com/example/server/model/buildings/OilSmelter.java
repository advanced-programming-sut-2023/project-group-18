package com.example.server.model.buildings;

import com.example.server.model.Governance;
import com.example.server.model.map.Tile;

public class OilSmelter extends IndustrialBuilding{
    private int oilCount;
    public OilSmelter(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
        this.oilCount = 0;
    }

    public void produce(){
        this.oilCount += this.getBuildingType().getProductionRate();
    }

    public void useOil(){
        this.oilCount --;
    }
}
