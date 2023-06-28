package com.example.model.buildings;

import com.example.model.Governance;
import com.example.model.map.Tile;

public class IndustrialBuilding extends Processing{
    private int remainingCapacity;

    public IndustrialBuilding(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
        this.remainingCapacity = buildingType.getCapacity();
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    @Override
    public void produce(){
        super.produce();
        this.remainingCapacity--;
    }



}
