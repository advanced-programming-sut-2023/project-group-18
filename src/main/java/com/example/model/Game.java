package com.example.model;

import java.util.ArrayList;

import com.example.model.map.GameMap;

public class Game {
    private final ArrayList<Governance> governances;
    private final GameMap gameMap;

    public Game(ArrayList<Governance> governances, GameMap gameMap) {
        this.governances = governances;
        this.gameMap = gameMap;
    }

    public ArrayList<Governance> getGovernances() {
        return governances;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

}
