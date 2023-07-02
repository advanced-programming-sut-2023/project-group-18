package com.example.client.model;

import com.example.client.model.assets.Asset;
import com.example.client.model.buildings.Building;
import com.example.client.model.buildings.BuildingType;
import com.example.client.model.buildings.Category;
import com.example.client.model.map.GameMap;
import com.example.client.model.people.Unit;
import com.example.client.view.controllers.GameMenuController;

import java.util.ArrayList;
import java.util.Random;

public class Game implements KeepLocations {
    private GameMenuController gameMenuController;
    private static Game instance;
    private GameMap gameMap;
    private final ArrayList<Governance> governances;
    private Governance currentGovernance;
    private Building selectedBuilding;
    private Unit selectedUnit;
    private int round;
    private int turn;
    private double mapCoefficient;

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
        dropKeeps();
        for (Governance governance : governances) {
            governance.addAssetToStorage(Asset.WOOD, 20);
            governance.addAssetToStorage(Asset.STONE, 20);
            governance.addAssetToStorage(Asset.IRON, 20);
            governance.addAssetToStorage(Asset.WHEAT, 20);
        }
    }

    private void dropKeeps() {
        Random random = new Random();
        int index = -1;
        for (Governance governance : governances) {
            index++;
            double x = COORDINATES[index][0] * mapCoefficient * GameMap.TILE_LENGTH + random.nextInt(-2, 3);
            double y = COORDINATES[index][1] * mapCoefficient * GameMap.TILE_LENGTH / 2 + random.nextInt(-2, 3);
            gameMap.setKeep(x, y, governance);
            currentGovernance = governance;
            gameMap.setSelectedBuilding(BuildingType.FIRE);
            gameMap.dropBuilding(x - GameMap.TILE_LENGTH * 5, y + GameMap.TILE_LENGTH * 5);
            governance.setLord();
            gameMap.setSelectedBuilding(BuildingType.STOCKPILE);
            gameMap.dropBuilding(x + GameMap.TILE_LENGTH * 5, y + GameMap.TILE_LENGTH * 5);
        }
        gameMap.setSelectedBuilding(null);
    }

    public void setGameMap(int length) {
        gameMap = new GameMap(length, this);
        mapCoefficient = length / 200.0d;
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
        if (selectedBuilding.getBuildingType().equals(BuildingType.KEEP))
            gameMenuController.showKeepMenu();
//        TODO: OMID
//        else if (selectedBuilding.getBuildingType().getCategory().equals(Category.TOWER) || selectedBuilding.getBuildingType().getCategory().equals(Category.WALL)) {
//            gameMenuController.repairMenu(selectedBuilding);
//        } else if (selectedBuilding.getBuildingType().getCategory().equals(Category.GATE)) {
//            gameMenuController.gateMenu(selectedBuilding);
//        } else if (selectedBuilding.getBuildingType().getCategory().equals(Category.BARRACKS)) {
//            gameMenuController.barracksMenu(selectedBuilding);
//        } else if (selectedBuilding.getBuildingType().getCategory().equals(Category.GUNSMITH)) {
//            gameMenuController.gunsmithMenu(selectedBuilding);
//        }
    }

    public Unit getSelectedUnit() {
        return selectedUnit;
    }

    public void selectUnit(Unit selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    public void setCurrentGovernance(Governance governance) {
        this.currentGovernance = governance;
    }

    public void setGameMenuController(GameMenuController gameMenuController) {
        this.gameMenuController = gameMenuController;
    }
}