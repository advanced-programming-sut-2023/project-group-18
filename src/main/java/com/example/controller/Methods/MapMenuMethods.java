package com.example.controller.Methods;

import com.example.model.Game;
import com.example.model.Map.Directions;
import com.example.model.Map.GameMap;
import com.example.view.MapMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenuMethods {
    private static MapMenuMethods mapMenuMethods;
    private final GameMenuMethods gameMenuMethods;
    private int xCoordinate;
    private int yCoordinate;
    private GameMap gameMap;

    private MapMenuMethods() {
        gameMenuMethods = GameMenuMethods.gameMenuMethods();
    }

    public static MapMenuMethods getInstance() {
        return mapMenuMethods == null ? mapMenuMethods = new MapMenuMethods() : mapMenuMethods;
    }

    public void run(Scanner scanner, int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        gameMap = GameMenuMethods.gameMenuMethods().getGame().getGameMap();
        MapMenu.getMapMenu().run(scanner);
    }

    public int getXCoordinate(Matcher matcher) {
        if (matcher.group("xCoordinate") != null) {
            return Integer.parseInt(matcher.group("xCoordinate"));
        } else {
            return Integer.parseInt(matcher.group("xCoordinate2"));
        }
    }

    public int getYCoordinate(Matcher matcher) {
        if (matcher.group("yCoordinate") != null) {
            return Integer.parseInt(matcher.group("yCoordinate"));
        } else {
            return Integer.parseInt(matcher.group("yCoordinate2"));
        }
    }

    public String showMap() {
        return gameMap.showMap(xCoordinate, yCoordinate);
    }

    public String showDetails(int x, int y) {
        return gameMap.showDetails(x, y);
    }

    public boolean directionValidation(String directionOne, String directionTwo) {
        return !((Directions.getDirectionByName(directionOne) == null) ||
                (directionTwo != null && Directions.getDirectionByName(directionTwo) == null));
    }

    public int getChange(int index, String directionOne, String directionTwo, int directionOneNumber, int directionTwoNumber) {
        return getMoveChange(directionOneNumber, directionOne, index) + getMoveChange(directionTwoNumber, directionTwo, index);
    }

    private int getMoveChange(int difference, String direction, int index) {
        if (direction == null)
            return 0;
        return Directions.getDirectionByName(direction).getCoordinate()[index] * difference;
    }

    public boolean moveMapValidation(int xDiff, int yDiff) {
        return gameMenuMethods.areCoordinatesValid(xCoordinate + xDiff, yCoordinate + yDiff);
    }

    public String moveMap(int xDiff, int yDiff) {
        xCoordinate += xDiff;
        yCoordinate += yDiff;
        return showMap();
    }
}
