package com.example.model.buildings;

import com.example.model.Governance;
import com.example.model.map.Tile;

public class Stair extends Wall{
    private Direction direction;
    public Stair(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
        this.direction = Direction.LEFT;
    }

    public Direction getDirection() {
        return direction;
    }

    //Todo
}
