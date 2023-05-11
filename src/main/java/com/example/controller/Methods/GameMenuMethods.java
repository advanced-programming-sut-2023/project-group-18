package com.example.controller.Methods;

import com.example.model.Assets.Asset;
import com.example.model.Buildings.Building;
import com.example.model.Buildings.BuildingType;
import com.example.model.Game;
import com.example.model.Governance;
import com.example.model.Map.Cell;
import com.example.model.Map.Texture;

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
        cell.setBuilding(new Building(buildingType, governance, cell));
    }
}
