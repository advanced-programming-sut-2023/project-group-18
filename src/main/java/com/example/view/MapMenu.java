package com.example.view;

import com.example.controller.Methods.MapMenuMethods;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu {
    private static MapMenu mapMenu;
    private final MapMenuMethods mapMenuMethods;
    private MapMenu() {
        mapMenuMethods = MapMenuMethods.getInstance();
    }
    public MapMenu getMapMenu() {
        return mapMenu == null ? mapMenu = new MapMenu() : mapMenu;
    }

    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
        }
    }
}
