package com.example.server.model.buildings;

import com.example.server.model.Governance;
import com.example.server.model.map.Tile;

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
