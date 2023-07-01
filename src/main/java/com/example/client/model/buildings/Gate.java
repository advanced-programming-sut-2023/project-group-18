package com.example.client.model.buildings;

import com.example.client.model.Governance;
import com.example.client.model.map.Tile;

public class Gate extends Tower {
    private boolean isOpen;
    private boolean hasBridge;
    private Direction direction;

    public Gate(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
        this.isOpen = true;
        this.hasBridge = false;
        this.direction = Direction.LEFT;
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

    @Override
    public boolean isReachable() {
        return hasLadder || isOpen;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCurrent state: " + (isOpen ? "open" : "close");
    }

}
