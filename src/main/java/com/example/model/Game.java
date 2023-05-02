package com.example.model;

import java.util.ArrayList;

import com.example.model.Buildings.BuildingType;
import com.example.model.People.SoldierType;

public class Game {
    private final GameMap gameMap;
    private final ArrayList<Governance> governances;
    private int players;
    private int round;
    private int turn;

    public Game(String size) {
        gameMap = new GameMap(size, this);
        governances = new ArrayList<>();
        players = round = turn = 0;
    }

    public int getPlayers() {
        return players;
    }

    public int getRound() {
        return round;
    }

    public int getTurn() {
        return turn;
    }

    public boolean isInGame(User user) {
        for (Governance governance : governances)
            if (governance.getOwner().equals(user)) return true;
        return false;
    }

    public void addPlayer(User user) {
        governances.add(new Governance(user));
        players++;
    }

    public void addBuilding(Governance governance, BuildingType buildingType, int xCordinate, int yCordinate) {
        governance.addBuilding(buildingType, gameMap.getTileByLocation(xCordinate, yCordinate));
    }

    public void addSoldier(Governance governance, SoldierType soldierType, int xCordinate, int yCordinate) {
        governance.addSoldier(new Cell(xCordinate, yCordinate), soldierType);
    }

    public void addNonMilitaryCharacters(Governance governance, int nonMilitaryCharacters) {
        governance.addNonMilitaryCharacters(nonMilitaryCharacters);
    }

}
