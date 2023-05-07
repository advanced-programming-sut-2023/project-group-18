package com.example.model.Buildings;

import com.example.model.Assets.Asset;
import com.example.model.Cell;
import com.example.model.Governance;

public class IndustrialBuilding extends Processing{
    private int remainingCapacity;

    public IndustrialBuilding(BuildingType buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
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
