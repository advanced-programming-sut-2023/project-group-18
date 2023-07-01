package com.example.model.buildings;

import com.example.model.Governance;
import com.example.model.map.Tile;

public class Stable extends Building {

    public Stable(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
    }

    @Override
    public String toString() {
        return super.toString() + "\nHorses: 4";
    }
}
