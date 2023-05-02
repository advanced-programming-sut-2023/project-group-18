package com.example.controller.Methods;

import com.example.model.Game;

public class GameMenuMethods {
    private static GameMenuMethods gameMenuMethods;
    private final Game game;

    private GameMenuMethods() {
        game = new Game("normal");
    }

    public static GameMenuMethods gameMenuMethods() {
        return  gameMenuMethods == null ? gameMenuMethods = new GameMenuMethods() : gameMenuMethods;
    }

    public boolean areCordinatesValid(int xCordinate, int yCordinate) {
        return game.getGameMap().areCordinatesValid(xCordinate, yCordinate);
    }

}
