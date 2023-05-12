package com.example.model;

import java.util.ArrayList;

import com.example.model.Buildings.Building;
import com.example.model.Buildings.BuildingType;
import com.example.model.Map.Cell;
import com.example.model.Map.GameMap;
import com.example.model.People.SoldierType;
import com.example.model.People.Unit;

public class Game {
    private final GameMap gameMap;
    private final ArrayList<Governance> governances;
    private Governance currentGovernance;
    private Building selectedBuilding;
    private Unit selectedUnit;
    private int players;
    private int round;
    private int turn;

    public Game(String size) {
        gameMap = new GameMap(size, this);
        governances = new ArrayList<>();
        players = round = turn = 0;
        currentGovernance = null;
        selectedBuilding = null;
    }

    public GameMap getGameMap() {
        return gameMap;
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
        governance.addBuilding(buildingType, gameMap.getCellByLocation(xCordinate, yCordinate));
    }

    public void addSoldier(Governance governance, SoldierType soldierType, int xCoordinate, int yCoordinate) {
        governance.addSoldier(new Cell(xCoordinate, yCoordinate,this.gameMap), soldierType);
    }

    public void addNonMilitaryCharacters(Governance governance, int nonMilitaryCharacters) {
        governance.addNonMilitaryCharacters(nonMilitaryCharacters);
    }

    public ArrayList<Governance> getGovernances() {
        return governances;
    }

    public Governance getCurrentGovernance() {
        return currentGovernance;
    }

    public Building getSelectedBuilding() {
        return selectedBuilding;
    }

    public void selectBuilding(Building selectedBuilding) {
        this.selectedBuilding = selectedBuilding;
    }

    public Unit getSelectedUnit() {
        return selectedUnit;
    }

    public void selectUnit(Unit selectedUnit) {
        if (selectedUnit.isControllable())
            this.selectedUnit = selectedUnit;
    }
}
