package com.example;

import com.example.model.GameMap;

public class Main {
    public static void main(String[] args) {
        GameMap gameMap = new GameMap("normal", null);
        System.out.println(gameMap.showMap(60, 60));
    }
}