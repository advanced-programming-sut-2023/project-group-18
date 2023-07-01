package com.example.server.model.map;

import com.example.server.model.people.Unit;
import com.example.server.model.people.UnitType;

public interface MapInterface {

    Tile getTileByIndex(int xIndex, int yIndex);

    void move(Unit unit, double x, double y);

    void dropBuilding(double x, double y);

    void dropUnit(UnitType unitType);


}
