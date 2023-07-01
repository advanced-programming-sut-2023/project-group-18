package com.example.model.map;

import com.example.model.people.Unit;
import com.example.model.people.UnitType;

public interface MapInterface {

    double TILE_LENGTH = 10.0d;

    Tile getTileByIndex(int xIndex, int yIndex);

    void move(Unit unit, double x, double y);

    void dropBuilding(double x, double y);

    void dropUnit(UnitType unitType);


}
