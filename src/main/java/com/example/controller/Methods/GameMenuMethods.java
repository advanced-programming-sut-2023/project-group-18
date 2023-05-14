package com.example.controller.Methods;

import com.example.model.Assets.Asset;
import com.example.model.Assets.AssetType;
import com.example.model.Buildings.Building;
import com.example.model.Buildings.BuildingType;
import com.example.model.Buildings.Gate;
import com.example.model.Buildings.Tower;
import com.example.model.ConsoleColors;
import com.example.model.Game;
import com.example.model.Governance;
import com.example.model.Map.Cell;
import com.example.model.Map.Texture;
import com.example.model.People.*;
import com.example.model.People.Soldier;
import com.example.model.People.SoldierType;
import com.example.model.People.Unit;
import com.example.model.People.UnitType;
import com.example.model.User;
import com.example.view.GameMenu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenuMethods implements ConsoleColors {
    private static GameMenuMethods gameMenuMethods;
    private final Game game;

    private GameMenuMethods() {
        game = Game.getInstance();
    }

    public static GameMenuMethods gameMenuMethods() {
        return gameMenuMethods == null ? gameMenuMethods = new GameMenuMethods() : gameMenuMethods;
    }

    public void run(Scanner scanner, ArrayList<User> players, String size) {
        game.setGameMap(size);
        game.makeNewGovernances(players);
        while (!game.getGovernances().isEmpty()){
            for (Governance governance : game.getGovernances()){
                game.setCurrentGovernance(governance);
                GameMenu.getGameMenu().run(scanner);
            }
            ArrayList<Governance> shouldRemoveGovernances = new ArrayList<>();
            for (Governance governance : game.getGovernances()){
                if (governance.getLord().getHitpoint() == 0)
                    shouldRemoveGovernances.add(governance);
            }
            game.getGovernances().removeAll(shouldRemoveGovernances);
            int score = 10 - game.getGovernances().size();
            for (Governance governance : shouldRemoveGovernances){
                governance.getOwner().addScore(score);
            }
        }
    }

    public Game getGame() {
        return game;
    }

    public boolean areCoordinatesValid(int xCoordinate, int yCoordinate) {
        return game.getGameMap().areCoordinatesValid(xCoordinate, yCoordinate);
    }

    public boolean isCellEmpty(int xCoordinate, int yCoordinate) {
        Cell cell = game.getGameMap().getCellByLocation(xCoordinate, yCoordinate);
        return cell.getBuilding() == null && cell.getUnits().isEmpty();
    }
    public boolean isNotEdge(BuildingType buildingType, int xCoordinate, int yCoordinate){
        int width = buildingType.getWidth() - 1;
        int mapSize = game.getGameMap().getMapSize();
        if ((xCoordinate >= width) && (xCoordinate <= mapSize - width - 1)){
            return (yCoordinate >= width) && (yCoordinate <= mapSize - width - 1);
        }
        return false;
    }
    public boolean isTextureCompatible(BuildingType buildingType, int xCoordinate, int yCoordinate) {
        Cell cell = game.getGameMap().getCellByLocation(xCoordinate, yCoordinate);
        int width = buildingType.getWidth();
        Texture properTexture = buildingType.getGroundType();
        for (int i = -width + 1; i < width; i++)
            for (int j = -width + 1; j < width; j++) {
                int x = cell.getxCoordinate() + i;
                int y = cell.getyCoordinate() + j;
                Cell underCell = cell.getGameMap().getCellByLocation(x, y);
                Texture texture = underCell.getTexture();
                if (properTexture.equals(Texture.GROUND)) {
                    if (texture.equals(Texture.LAWN) || texture.equals(Texture.RARE_GRASSLAND))
                        continue;
                }
                if (!properTexture.equals(texture))
                    return false;
            }
        return true;
    }

    public boolean haveEnoughResources(BuildingType buildingType) {
        int goldNeeded = buildingType.getGoldCost();
        Asset resourceType = buildingType.getResourceType();
        int resourceNeeded = buildingType.getResourceCost();
        Governance governance = game.getCurrentGovernance();
        return governance.getGold() >= goldNeeded && governance.getAssetCount(resourceType) >= resourceNeeded;
    }

    public void dropBuilding(BuildingType buildingType, int xCoordinate, int yCoordinate) {
        Cell cell = game.getGameMap().getCellByLocation(xCoordinate, yCoordinate);
        Governance governance = game.getCurrentGovernance();
        int goldNeeded = buildingType.getGoldCost();
        Asset resourceType = buildingType.getResourceType();
        int resourceNeeded = buildingType.getResourceCost();
        governance.addGold(-goldNeeded);
        governance.removeAssetFromStorage(resourceType, resourceNeeded);
        governance.addBuilding(buildingType, cell);
    }

    public boolean coordinatesHasBuilding(int xCoordinate, int yCoordinate) {
        Cell cell = game.getGameMap().getCellByLocation(xCoordinate, yCoordinate);
        return cell.getBuilding() != null;
    }

    public boolean isCurrentGovernanceOwner(int xCoordinate, int yCoordinate) {
        Building building = game.getGameMap().getCellByLocation(xCoordinate, yCoordinate).getBuilding();
        return building.getGovernance().equals(game.getCurrentGovernance());
    }

    public Building getCoordinatesBuildingType(int xCoordinate, int yCoordinate) {
        return game.getGameMap().getCellByLocation(xCoordinate, yCoordinate).getBuilding();
    }

    public void selectBuilding(int xCoordinate, int yCoordinate, Scanner scanner) {
        Building building = getCoordinatesBuildingType(xCoordinate, yCoordinate);
        game.selectBuilding(building);
        building.getBuildingType().getSelectBuildingMenuMethods().run(scanner);
    }

    public boolean checkCreateUnitCommandValid(String type, int count) {
        return SoldierType.getSoldierTypeByName(type) != null && count > 0;
    }

    public boolean isSelectedBuildingBarracks() {
        Building building = game.getSelectedBuilding();
        BuildingType barracks = BuildingType.BARRACKS;
        BuildingType mercenaryPost = BuildingType.MERCENARY_POST;
        BuildingType cathedral = BuildingType.CATHEDRAL;
        BuildingType guild = BuildingType.ENGINEER_GUILD;
        BuildingType type = building.getBuildingType();
        return type.equals(barracks) || type.equals(mercenaryPost) || type.equals(cathedral) || type.equals(guild);
    }

    public boolean isCompatibleWithBarracks(String type) {
        BuildingType barracksNeeded = SoldierType.getSoldierTypeByName(type).getBuildingType();
        return barracksNeeded.equals(game.getSelectedBuilding().getBuildingType());
    }

    public boolean haveEnoughPeople(int count) {
        Governance governance = game.getCurrentGovernance();
        int remainingPeople = governance.getRemainingNonMilitary() - governance.getSoldiersCreatedInTurn();
        return remainingPeople >= count;
    }

    public boolean haveEnoughResourcesForTroop(String type, int count) {
        Governance governance = game.getCurrentGovernance();
        SoldierType soldierType = SoldierType.getSoldierTypeByName(type);
        int goldCost = soldierType.getCost();
        Asset weapon = soldierType.getWeapon();
        Asset armor = soldierType.getArmor();
        return governance.canRemoveAssetFromStorage(weapon, count) && governance.getGold() >= (goldCost * count)
                && governance.canRemoveAssetFromStorage(armor, count);
    }

    public void deployTroop(String type, int count) {
        Governance governance = game.getCurrentGovernance();
        SoldierType soldierType = SoldierType.getSoldierTypeByName(type);
        int goldCost = soldierType.getCost();
        Asset weapon = soldierType.getWeapon();
        Asset armor = soldierType.getArmor();
        governance.addGold(-goldCost);
        governance.removeAssetFromStorage(weapon, count);
        governance.removeAssetFromStorage(armor, count);
        governance.createSoldier(count);
        Cell barracksCell = game.getSelectedBuilding().getCell();
        switch (soldierType){
            case LADDERMEN -> new LadderMan(barracksCell, governance, soldierType);
            case TUNNELER -> new Tunneler(barracksCell, governance, soldierType);
            case ENGINEER -> new Engineer(barracksCell, governance, soldierType);
            default -> new Soldier(barracksCell, governance, soldierType);
        }
    }

    public boolean canRepair() {
        Building building = game.getSelectedBuilding();
        return building instanceof Tower;
    }

    public boolean haveEnoughAssetForRepair() {
        Tower tower = (Tower) game.getSelectedBuilding();
        return tower.canRepair();
    }

    public boolean haveEnemyInNeighbourCells() {
        Building building = game.getSelectedBuilding();
        ArrayList<Cell> neighbourCells = game.getGameMap().neighbourCells(building.getCell());
        neighbourCells.add(building.getCell());
        for (Cell cell : neighbourCells) {
            if (cell.hasEnemy(game.getCurrentGovernance()))
                return true;
        }
        return false;
    }

    public boolean haveSoldierInCell(int xCoordinate, int yCoordinate) {
        Cell cell = game.getGameMap().getCellByLocation(xCoordinate, yCoordinate);
        Governance governance = game.getCurrentGovernance();
        for (Unit unit : cell.getUnits()) {
            if (unit.isControllable()) {
                if (unit.getGovernance().equals(governance))
                    return true;
            }
        }
        return false;
    }

    public void selectUnit(int xCoordinate, int yCoordinate) {
        Cell cell = game.getGameMap().getCellByLocation(xCoordinate, yCoordinate);
        Governance governance = game.getCurrentGovernance();
        for (Unit unit : cell.getUnits()) {
            if (unit.isControllable()) {
                if (unit.getGovernance().equals(governance)) {
                    game.selectUnit(unit);
                    return;
                }
            }
        }
    }

    public boolean isDestinationValid(int xCoordinate, int yCoordinate) {
        Cell cell = game.getGameMap().getCellByLocation(xCoordinate, yCoordinate);
        Texture texture = cell.getTexture();
        return texture.isReachable();
    }

    public boolean canMove(int xCoordinate, int yCoordinate){
        Cell destination = game.getGameMap().getCellByLocation(xCoordinate, yCoordinate);
        Unit selectedUnit = game.getSelectedUnit();
        selectedUnit.setTargetCell(destination);
        selectedUnit.findPath();
        boolean output = selectedUnit.getPath().size() != 0;
        selectedUnit.getPath().clear();
        return output;
    }

    public void move(int xCoordinate, int yCoordinate) {
        Cell destination = game.getGameMap().getCellByLocation(xCoordinate, yCoordinate);
        Unit selectedUnit = game.getSelectedUnit();
        selectedUnit.setTargetCell(destination);
        selectedUnit.findPath();
        selectedUnit.movePath();
    }

    public void patrol(int xCoordinate, int yCoordinate) {
        Cell destination = game.getGameMap().getCellByLocation(xCoordinate, yCoordinate);
        Unit selectedUnit = game.getSelectedUnit();
        selectedUnit.setPatrolCell(selectedUnit.getUnitCell());
        selectedUnit.setTargetCell(destination);
        selectedUnit.findPath();
    }

    public void cancelPatrol() {
        Unit selectedUnit = game.getSelectedUnit();
        selectedUnit.setPatrolCell(null);
        selectedUnit.setTargetCell(null);
        selectedUnit.getPath().clear();
    }

    public boolean checkIsGate() {
        return game.getSelectedBuilding() instanceof Gate;
    }

    public void closeGate() {
        Gate gate = (Gate) game.getSelectedBuilding();
        gate.close();
    }

    public void openGate() {
        Gate gate = (Gate) game.getSelectedBuilding();
        gate.open();
    }

    public BuildingType getBuildingType(String buildingTypeName) {
        for (BuildingType buildingType : BuildingType.values()) {
            if (buildingType.getName().equals(buildingTypeName)) {
                return buildingType;
            }
        }
        return null;
    }

    public boolean inRange(int x, int y) {
        return (x >= 0 && y >= 0 && x < game.getGameMap().getMapSize() && y < game.getGameMap().getMapSize());
    }

    public String showAssets() {
        Governance governance = game.getCurrentGovernance();
        String result = GREEN_BOLD + "Gold: " + governance.getGold() + RESET;
        for (AssetType assetType : governance.getAssets().keySet()) {
            result += BLUE_BOLD + "\n" + assetType.getName() + ":" + RESET;
            for (Asset asset : governance.getAssets().get(assetType).keySet())
                result += "\n" + asset.getName() + ": " + governance.getAssets().get(assetType).get(asset);
        }
        return result;
    }


    /*--------------------------------------------------------------------------------------------------------------------------------*/

    public int getXCoordinate(Matcher matcher) {
        if (matcher.group("xCoordinate") != null) {
            return Integer.parseInt(matcher.group("xCoordinate"));
        } else {
            return Integer.parseInt(matcher.group("xCoordinate2"));
        }
    }

    public int getYCoordinate(Matcher matcher) {
        if (matcher.group("yCoordinate") != null) {
            return Integer.parseInt(matcher.group("yCoordinate"));
        } else {
            return Integer.parseInt(matcher.group("yCoordinate2"));
        }
    }

    public int getCount(Matcher matcher) {
        if (matcher.group("count") != null) {
            return Integer.parseInt(matcher.group("count"));
        }
        return Integer.parseInt(matcher.group("count2"));
    }

    public String getType(Matcher matcher) {
        if (matcher.group("type") != null) {
            return matcher.group("type");
        }
        return matcher.group("type2");
    }

    public HashMap<String, String> sortFields(ArrayList<String> fields) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (String string : fields) {
            String quoteSubstring = string.trim().substring(4, string.trim().length() - 1);
            boolean isQuoted = string.trim().charAt(3) == '\"' && string.trim().endsWith("\"");
            if (isQuoted) {
                hashMap.put(string.trim().substring(0, 2), quoteSubstring);
            } else
                hashMap.put(string.trim().substring(0, 2), string.trim().substring(3));
        }
        return hashMap;
    }

    public boolean checkPatrolUnitInvalidField(HashMap<String, String> hashMap) {
        for (String string : hashMap.keySet()) {
            if (!string.equals("-x1") && !string.equals("-x2") && !string.equals("-y1") && !string.equals("-y2"))
                return false;
        }
        return hashMap.size() == 4;
    }

    public boolean isEngineer(){
        return game.getSelectedUnit() instanceof Engineer;
    }

    public void buildAttackTool(int xCoordinate, int yCoordinate){
        dropBuilding(BuildingType.SIEGE_TENT, xCoordinate, yCoordinate);
    }

    public boolean checkDropBuildingInvalidField(HashMap<String, String> hashMap) {
        for (String string : hashMap.keySet()) {
            if (!string.trim().equals("-x") && !string.trim().equals("-y") && !string.trim().equals("-t"))
                return false;
        }
        return hashMap.size() == 3;
    }

}
