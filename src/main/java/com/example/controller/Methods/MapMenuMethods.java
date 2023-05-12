package com.example.controller.Methods;

import java.util.regex.Matcher;

public class MapMenuMethods {
    private static MapMenuMethods mapMenuMethods;

    private MapMenuMethods() {
    }

    public static MapMenuMethods getInstance() {
        return mapMenuMethods == null ? mapMenuMethods = new MapMenuMethods() : mapMenuMethods;
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
}
