package com.example.view;

import com.example.controller.Commands.MapMenuCommands;
import com.example.controller.Methods.GlobalMethods;
import com.example.controller.Methods.MapMenuMethods;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu {
    private static MapMenu mapMenu;
    private final MapMenuMethods mapMenuMethods;
    private final GlobalMethods globalMethods;


    private MapMenu() {
        mapMenuMethods = MapMenuMethods.getInstance();
        globalMethods = GlobalMethods.getInstance();
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
        String directionOne, directionTwo;
        int directionOneNumber, directionTwoNumber;
        directionOne = matcher.group("directionOne");
        if (matcher.group("directionOneNumber") != null) {
            directionOneNumber = Integer.parseInt(matcher.group("directionOneNumber"));
        }
        if (matcher.group("directionTwo") != null) {
            directionTwo = matcher.group("directionTwo");
        }
        if (matcher.group("directionTwoNumber") != null) {
            directionTwoNumber = Integer.parseInt(matcher.group("directionTwoNumber"));
        }
    }

    private void showDetails(Matcher matcher) {
        int xCoordinate = mapMenuMethods.getXCoordinate(matcher);
        int yCoordinate = mapMenuMethods.getYCoordinate(matcher);
    }
}
