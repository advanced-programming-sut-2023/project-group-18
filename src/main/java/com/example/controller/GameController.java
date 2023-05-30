package com.example.controller;

public class GameController {
    private static GameController controller;

    private GameController() {

    }

    public static GameController getInstance() {
        return controller == null ? controller = new GameController() : controller;
    }

}
