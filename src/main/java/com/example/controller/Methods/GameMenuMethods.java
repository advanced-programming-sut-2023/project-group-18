package com.example.controller.Methods;

import com.example.model.Assets.Asset;
import com.example.model.Buildings.Building;
import com.example.model.Buildings.BuildingType;
import com.example.model.Game;
import com.example.model.Governance;
import com.example.model.Map.Cell;
import com.example.model.Map.Texture;
import com.example.model.People.Soldier;
import com.example.model.People.SoldierType;
import com.example.model.People.UnitType;

public class GameMenuMethods {
    private static GameMenuMethods gameMenuMethods;
    private final Game game;
    private GameMenuMethods() {
        game = new Game("normal");
    }

    public static GameMenuMethods gameMenuMethods() {
        return  gameMenuMethods == null ? gameMenuMethods = new GameMenuMethods() : gameMenuMethods;
    }

    public boolean areCoordinatesValid(int xCoordinate, int yCoordinate) {
        return game.getGameMap().areCordinatesValid(xCoordinate, yCoordinate);
    }

    public boolean isCellEmpty(int xCoordinate, int yCoordinate){
        Cell cell = game.getGameMap().getCellByLocation(xCoordinate, yCoordinate);
        return cell.getBuilding() == null && cell.getUnits().isEmpty();
    }

    public boolean isTextureCompatible(BuildingType buildingType, int xCoordinate, int yCoordinate){
        Cell cell = game.getGameMap().getCellByLocation(xCoordinate, yCoordinate);
        if (buildingType.getGroundType().equals(Texture.GROUND)){
            if (cell.getTexture().equals(Texture.GRAVEL))
                return true;
        }
        return buildingType.getGroundType().equals(cell.getTexture());
    }

    public boolean haveEnoughResources(BuildingType buildingType){
        int goldNeeded = buildingType.getGoldCost();
        Asset resourceType = buildingType.getResourceType();
        int resourceNeeded = buildingType.getResourceCost();
        Governance governance = game.getCurrentGovernance();
        return governance.getGold() >= goldNeeded && governance.getAssetCount(resourceType) >= resourceNeeded;
    }

    public void dropBuilding(BuildingType buildingType, int xCoordinate, int yCoordinate){
        Cell cell = game.getGameMap().getCellByLocation(xCoordinate, yCoordinate);
        Governance governance = game.getCurrentGovernance();
        int goldNeeded = buildingType.getGoldCost();
        Asset resourceType = buildingType.getResourceType();
        int resourceNeeded = buildingType.getResourceCost();
        governance.addGold( - goldNeeded);
        governance.removeAssetFromStorage(resourceType, resourceNeeded);
        cell.setBuilding(new Building(buildingType, governance, cell));
    }

    public boolean coordinatesHasBuilding(int xCoordinate, int yCoordinate){
        Cell cell = game.getGameMap().getCellByLocation(xCoordinate, yCoordinate);
        return cell.getBuilding() != null;
    }

    public boolean isCurrentGovernanceOwner(int xCoordinate, int yCoordinate){
        Building building = game.getGameMap().getCellByLocation(xCoordinate, yCoordinate).getBuilding();
        return building.getGovernance().equals(game.getCurrentGovernance());
    }

    public Building getCoordinatesBuildingType(int xCoordinate, int yCoordinate){
        return game.getGameMap().getCellByLocation(xCoordinate, yCoordinate).getBuilding();
    }

    public void selectBuilding(int xCoordinate, int yCoordinate){
        Building building = getCoordinatesBuildingType(xCoordinate, yCoordinate);
        game.selectBuilding(building);
    }

    public boolean checkCreateUnitCommandValid(String type, int count){
        return SoldierType.getSoldierTypeByName(type) != null && count > 0;
    }
    public boolean isSelectedBuildingBarracks(){
        Building building = game.getSelectedBuilding();
        BuildingType barracks = BuildingType.BARRACKS;
        BuildingType mercenaryPost = BuildingType.MERCENARY_POST;
        BuildingType cathedral = BuildingType.CATHEDRAL;
        BuildingType guild = BuildingType.ENGINEER_GUILD;
        BuildingType type = building.getBuildingType();
        return type.equals(barracks) || type.equals(mercenaryPost) || type.equals(cathedral) || type.equals(guild);
    }

    public boolean isCompatibleWithBarracks(String type){
        BuildingType barracksNeeded = SoldierType.getSoldierTypeByName(type).getBuildingType();
        return barracksNeeded.equals(game.getSelectedBuilding().getBuildingType());
    }

    public boolean haveEnoughPeople(int count){
        Governance governance = game.getCurrentGovernance();
        int remainingPeople = governance.getRemainingNonMilitary() - governance.getSoldiersCreatedInTurn();
        return remainingPeople >= count;
    }

    public boolean haveEnoughResourcesForTroop(String type, int count){
        Governance governance = game.getCurrentGovernance();
        SoldierType soldierType = SoldierType.getSoldierTypeByName(type);
        int goldCost = soldierType.getCost();
        Asset weapon = soldierType.getWeapon();
        Asset armor = soldierType.getArmor();
        return governance.canRemoveAssetFromStorage(weapon, count) && governance.getGold() >= (goldCost * count)
                && governance.canRemoveAssetFromStorage(armor, count);
    }

    public void deployTroop(String type, int count){
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
        Soldier soldier = new Soldier(barracksCell, governance, soldierType);
        barracksCell.addUnit(soldier);
    }
}
