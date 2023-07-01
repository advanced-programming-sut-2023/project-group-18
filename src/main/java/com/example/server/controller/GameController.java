package com.example.server.controller;

import com.example.server.model.Game;

public class GameController {
    private static GameController controller;
    private Game game;

    
    private GameController() {
        game = Game.getInstance();
    }

    public static GameController getInstance() {
        return controller == null ? controller = new GameController() : controller;
    }
    
    public Game getGame() {
        return game;
    }
    
}
