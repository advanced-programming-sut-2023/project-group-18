package com.example.model.Buildings;

import com.example.model.Assets.Asset;
import com.example.model.Map.Cell;
import com.example.model.Governance;

public class Gate extends Tower{
    private boolean isOpen;
    private boolean hasBridge;
    private final String direction;

    public Gate(BuildingType buildingType, Governance governance, Cell cell, String direction) {
        super(buildingType, governance, cell);
        this.isOpen = false;
        this.hasBridge = false;
        this.direction = direction;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean hasBridge() {
        return hasBridge;
    }

    public void close(){
        isOpen = false;
    }

    public void open(){
        isOpen = true;
    }

    public void makeBridge(){
        hasBridge = true;
        this.hitpoint += 100;
    }
    public String getDirection() {
        return direction;
    }
}
