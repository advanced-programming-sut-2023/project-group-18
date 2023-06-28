package com.example.model.map;

import com.example.model.people.Unit;
import com.example.model.people.UnitType;

import javafx.geometry.Point2D;

public interface MapInterface {

    Tile getTileByIndex(int xIndex, int yIndex);

    void move(Unit unit, Point2D start, Point2D dest);

    void dropBuilding(double x, double y);

    void dropUnit(UnitType unitType);


}
