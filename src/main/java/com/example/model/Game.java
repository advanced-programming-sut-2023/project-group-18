package com.example.model;

import java.util.ArrayList;
import java.util.Random;

import com.example.model.assets.Asset;
import com.example.model.buildings.Building;
import com.example.model.buildings.BuildingType;
import com.example.model.map.GameMap;
import com.example.model.people.SoldierType;
import com.example.model.people.Unit;

public class Game implements KeepLocations {
    private static Game instance;
    private GameMap gameMap;
    private final ArrayList<Governance> governances;
    private Governance currentGovernance;
    private Building selectedBuilding;
    private Unit selectedUnit;
    private int round;
    private int turn;
    private int mapCoefficient;

    private Game() {
        governances = new ArrayList<>();
        round = turn = 0;
        selectedBuilding = null;
    }

    public static Game getInstance() {
        return instance == null ? instance = new Game() : instance;
    }

    public void makeNewGovernances(ArrayList<User> users) {
        for (User user : users)
            governances.add(new Governance(user));
        setCurrentGovernance(governances.get(0));
        // dropKeeps();
        // for (Governance governance : governances) {
        //     governance.addAssetToStorage(Asset.WOOD, 20);
        //     governance.addAssetToStorage(Asset.STONE, 20);
        //     governance.addAssetToStorage(Asset.IRON, 20);
        //     governance.addAssetToStorage(Asset.WHEAT, 20);
        // }
    }

    // TODO: need to change
    private void dropKeeps() {
        Random random = new Random();
        int index = -1;
        for (Governance governance : governances) {
            index++;
            int xCoordinate = COORDINATES[index][0] * mapCoefficient + random.nextInt(-2, 3);
            int yCoordinate = COORDINATES[index][1] * mapCoefficient + random.nextInt(-2, 3);
            Cell cell = gameMap.getCellByLocation(xCoordinate, yCoordinate);
            // initTexture(COORDINATES[index], random);
            governance.addBuilding(BuildingType.KEEP, cell);
            governance.setLord();
            Cell stockpileCell = gameMap.getCellByLocation(xCoordinate + 4, yCoordinate + 4);
            governance.addBuilding(BuildingType.STOCKPILE, stockpileCell);
        }
    }

    public void setGameMap(int length) {
        gameMap = new GameMap(length, this);
        if (length == 100) mapCoefficient = 1;
        else mapCoefficient = 2;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public String showTurn() {
        return "Turn: " + turn + "\nRound: " + round;
    }

    public void nextTurn() {
        turn = governances.indexOf(currentGovernance);
        if (turn == 0) round++;
    }

    public boolean isInGame(User user) {
        for (Governance governance : governances)
            if (governance.getOwner().equals(user)) return true;
        return false;
    }

    
    

    public void addSoldier(Governance governance, SoldierType soldierType, int xCoordinate, int yCoordinate) {
        // governance.addSoldier(new Cell(xCoordinate, yCoordinate,this.gameMap), soldierType);
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

    public void setCurrentGovernance(Governance governance){
        this.currentGovernance = governance;
    }

}
