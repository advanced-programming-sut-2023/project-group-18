package com.example.model.map;

import com.example.model.buildings.Building;
import com.example.model.people.Unit;

import javafx.geometry.Point2D;

public interface MapInterface {

    Tile getTileByLocation(Point2D location);

    void move(Unit unit, Point2D start, Point2D dest);

    void dropBuilding(Building building, Point2D point2d);

    void dropUnit(Unit unit, Point2D point2d);


}
