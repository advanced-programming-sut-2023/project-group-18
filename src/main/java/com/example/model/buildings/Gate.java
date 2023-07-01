package com.example.model.buildings;

import com.example.model.Governance;
import com.example.model.map.Tile;

public class Gate extends Tower{
    private boolean isOpen;

    public Gate(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
        this.isOpen = true;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void close() {
        isOpen = false;
    }

    public void open() {
        isOpen = true;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCurrent state: " + (isOpen ? "open" : "close");
    }

}
