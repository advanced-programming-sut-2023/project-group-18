package com.example.client.model.map;

import com.example.client.model.people.Unit;
import com.example.client.model.people.UnitType;

public interface MapInterface {

    Tile getTileByIndex(int xIndex, int yIndex);

    void move(Unit unit, double x, double y);

    void dropBuilding(double x, double y);

    void dropUnit(UnitType unitType);


}
