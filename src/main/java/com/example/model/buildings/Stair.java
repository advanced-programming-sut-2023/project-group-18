package com.example.model.buildings;

import com.example.model.Governance;
import com.example.model.map.Tile;

public class Stair extends Wall {
    public Stair(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
    }
}
