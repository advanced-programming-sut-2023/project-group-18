package com.example.controller.Methods;

import com.example.model.Game;
import com.example.model.Map.GameMap;
import com.example.view.MapMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenuMethods {
    private static MapMenuMethods mapMenuMethods;
    private int xCoordinate;
    private int yCoordinate;
    private GameMap gameMap;

    private MapMenuMethods() {
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
}
