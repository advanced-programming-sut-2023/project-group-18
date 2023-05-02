package com.example.controller.Methods;

public class GameMenuMethods {
    private static GameMenuMethods gameMenuMethods;
    private GameMenuMethods() {
    }
    public static GameMenuMethods gameMenuMethods() {
        return  gameMenuMethods == null ? gameMenuMethods = new GameMenuMethods() : gameMenuMethods;
    }

    

}
