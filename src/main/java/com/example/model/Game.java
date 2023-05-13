package com.example.model;

import java.util.ArrayList;

import com.example.model.Assets.Asset;
import com.example.model.Buildings.Building;
import com.example.model.Buildings.BuildingType;
import com.example.model.Buildings.Tree;
import com.example.model.Map.Cell;
import com.example.model.Map.GameMap;
import com.example.model.People.SoldierType;
import com.example.model.People.Unit;

public class Game {
    private static Game instance;
    private GameMap gameMap;
    private final ArrayList<Governance> governances;
    private int players;
    private Governance currentGovernance;
    private Building selectedBuilding;
    private Unit selectedUnit;
    private int round;
    private int turn;
    private final ArrayList<Tree> trees;

    private Game() {
        governances = new ArrayList<>();
        trees = new ArrayList<>();
        players = round = turn = 0;
        selectedBuilding = null;
    }

    public static Game getInstance() {
        return instance == null ? instance = new Game() : instance;
    }

    public void makeNewGovernances(ArrayList<User> users) {
        for (User user : users)
            governances.add(new Governance(user));
        players = users.size();
        currentGovernance = governances.get(0);
        KeepLocations keepLocations;
        if (gameMap.getMapSize() == 200) keepLocations = new NormalMap();
        else keepLocations = new LargeMap();
        keepLocations.dropKeeps(this);
        for (Governance governance : governances) {
            governance.addAssetToStorage(Asset.WOOD, 50);
        }
    }

    public void setGameMap(String size) {
        gameMap = new GameMap(size, this);
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

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    public void addTree(Tree tree) {
        trees.add(tree);
    }

    public void removeTree(Tree tree) {
        trees.remove(tree);
    }

}
