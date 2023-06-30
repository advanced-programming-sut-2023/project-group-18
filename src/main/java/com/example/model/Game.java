package com.example.model;

import java.util.ArrayList;
import java.util.Random;

import com.example.controller.GameController;
import com.example.model.assets.Asset;
import com.example.model.buildings.Building;
import com.example.model.buildings.BuildingType;
import com.example.model.buildings.Category;
import com.example.model.map.GameMap;
import com.example.model.people.SoldierType;
import com.example.model.people.Unit;
import com.example.model.people.UnitType;
import com.example.view.controllers.GameMenuController;

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
        if (buildingActionNeeded(selectedBuilding.getBuildingType())) {
            if (selectedBuilding.getBuildingType().equals(BuildingType.KEEP))
                gameMenuController.showKeepMenu();
            else if (selectedBuilding.getBuildingType().getCategory().equals(Category.TOWER) || selectedBuilding.getBuildingType().getCategory().equals(Category.WALL)) {
                gameMenuController.repairMenu(selectedBuilding);
            } else if (selectedBuilding.getBuildingType().getCategory().equals(Category.GATE)) {
                gameMenuController.gateMenu(selectedBuilding);
            }
//            }
        }
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

    public boolean buildingActionNeeded(BuildingType buildingType) {
        return (buildingType.equals(BuildingType.KEEP) || buildingType.equals(BuildingType.MARKET) || buildingType.equals(BuildingType.WALL)
                || buildingType.equals(BuildingType.SQUARE_TOWER) || buildingType.equals(BuildingType.LOOKOUT_TOWER) || buildingType.equals(BuildingType.PERIMETER_TOWER)
                || buildingType.equals(BuildingType.CIRCLE_TOWER) || buildingType.equals(BuildingType.SMALL_STONE_GATEHOUSE)
                || buildingType.equals(BuildingType.BIG_STONE_GATEHOUSE) || buildingType.equals(BuildingType.BARRACKS) || buildingType.equals(BuildingType.ARMOURER)
                || buildingType.equals(BuildingType.FLETCHER) || buildingType.equals(BuildingType.POLETURNER) || buildingType.equals(BuildingType.MERCENARY_POST));
    }

    public void setGameMenuController(GameMenuController gameMenuController) {
        this.gameMenuController = gameMenuController;
    }
}
