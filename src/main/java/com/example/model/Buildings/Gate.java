package com.example.model.Buildings;

import com.example.model.Governance;
import com.example.model.Map.Cell;

public class Gate extends Tower{
    private boolean isOpen;
    private boolean hasBridge;
    private final Direction direction;

    public Gate(BuildingType buildingType, Governance governance, Cell cell, Direction direction) {
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
    public Direction getDirection() {
        return direction;
    }
}
