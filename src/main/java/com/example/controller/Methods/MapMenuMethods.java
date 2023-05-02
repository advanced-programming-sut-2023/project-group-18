package com.example.controller.Methods;

public class MapMenuMethods {
    private static MapMenuMethods mapMenuMethods;

    private MapMenuMethods() {
    }

    public static MapMenuMethods getInstance() {
        return mapMenuMethods == null ? mapMenuMethods = new MapMenuMethods() : mapMenuMethods;
    }

}
