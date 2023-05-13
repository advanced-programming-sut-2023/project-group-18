package com.example.view;

import com.example.controller.Commands.MapMenuCommands;
import com.example.controller.Methods.GameMenuMethods;
import com.example.controller.Methods.GlobalMethods;
import com.example.controller.Methods.MapMenuMethods;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu {
    private static MapMenu mapMenu;
    private final MapMenuMethods mapMenuMethods;
    private final GlobalMethods globalMethods;
    private final GameMenuMethods gameMenuMethods;


    private MapMenu() {
        mapMenuMethods = MapMenuMethods.getInstance();
        globalMethods = GlobalMethods.getInstance();
        gameMenuMethods = GameMenuMethods.gameMenuMethods();
    }

    public static MapMenu getMapMenu() {
        return mapMenu == null ? mapMenu = new MapMenu() : mapMenu;
    }

    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        System.out.println(MapMenuMethods.getInstance().showMap());
        while (true) {
            input = scanner.nextLine();
            if ((matcher = MapMenuCommands.getMatcher(input, MapMenuCommands.MOVE_MAP)).find()) {
                moveMap(matcher);
            } else if ((matcher = MapMenuCommands.getMatcher(input, MapMenuCommands.SHOW_DETAILS)).find()) {
                showDetails(matcher);
            } else if (MapMenuCommands.getMatcher(input, MapMenuCommands.EXIT).find()) {
                break;
            } else {
                globalMethods.invalidCommand();
            }
        }
    }

    private void moveMap(Matcher matcher) {
        String directionOne, directionTwo = "";
        int directionOneNumber = 1, directionTwoNumber = 1;
        directionOne = matcher.group("directionOne");
        if (matcher.group("directionOneNumber") != null) {
            directionOneNumber = Integer.parseInt(matcher.group("directionOneNumber").trim());
        }
        if (matcher.group("directionTwo") != null) {
            directionTwo = matcher.group("directionTwo");
        }
        if (matcher.group("directionTwoNumber") != null) {
            directionTwoNumber = Integer.parseInt(matcher.group("directionTwoNumber").trim());
        }
        if (!mapMenuMethods.directionValidation(directionOne,directionTwo)) {
            System.out.println("your input direction is invalid");
            return;
        }
        int xDiff = mapMenuMethods.getChange(0, directionOne, directionTwo, directionOneNumber, directionTwoNumber);
        int yDiff = mapMenuMethods.getChange(1, directionOne, directionTwo, directionOneNumber, directionTwoNumber);
        if (!mapMenuMethods.moveMapValidation(xDiff, yDiff)) {
            System.out.println("can't move to this coordinates");
            return;
        }
        System.out.println(mapMenuMethods.moveMap(xDiff, yDiff));
    }

    private void showDetails(Matcher matcher) {
        int xCoordinate = mapMenuMethods.getXCoordinate(matcher);
        int yCoordinate = mapMenuMethods.getYCoordinate(matcher);
        if (!gameMenuMethods.areCoordinatesValid(xCoordinate, yCoordinate)) {
            System.out.println("your entered coordination's are not valid");
            return;
        }
        System.out.println(mapMenuMethods.showDetails(xCoordinate, yCoordinate));
    }
}
