package com.example.client.model.buildings;

import com.example.client.model.Governance;
import com.example.client.model.map.Tile;

public class OilSmelter extends IndustrialBuilding {
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
