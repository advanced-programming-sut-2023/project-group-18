package com.example.model.buildings;

import com.example.model.Governance;
import com.example.model.map.Cell;

public class Stair extends Wall{
    private final Direction direction;
    public Stair(BuildingType buildingType, Governance governance, Cell cell, Direction direction) {
        super(buildingType, governance, cell);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    //Todo
}
