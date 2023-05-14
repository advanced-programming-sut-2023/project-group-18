package com.example.model;

import java.util.Random;

import com.example.controller.Methods.GameMenuMethods;
import com.example.model.Buildings.BuildingType;
import com.example.model.Map.Cell;
import com.example.model.Map.Texture;

public interface KeepLocations {
    int[][] COORDINATES = {{50, 50}, {50, 100}, {50, 150}, {100, 50}, {100, 150}, {150, 50}, {150, 100}, {150, 150}};

    void dropKeeps(Game game);

}


class NormalMap implements KeepLocations {
    @Override
    public void dropKeeps(Game game) {
        Random random = new Random();
        int index = -1;
        for (Governance governance : game.getGovernances()) {
            index++;
            int xCoordinate = COORDINATES[index][0] + random.nextInt(-4, 5);
            int yCoordinate = COORDINATES[index][1] + random.nextInt(-4, 5);
            Cell cell = game.getGameMap().getCellByLocation(xCoordinate, yCoordinate);
            cell.setTexture(Texture.GROUND);
            governance.addBuilding(BuildingType.KEEP, cell);
            Cell stockpileCell = game.getGameMap().getCellByLocation(xCoordinate + 6, yCoordinate + 6);
            stockpileCell.setTexture(Texture.GROUND);
            governance.addBuilding(BuildingType.STOCKPILE, stockpileCell);
        }
    }
}

class LargeMap implements KeepLocations {
    @Override
    public void dropKeeps(Game game) {
        Random random = new Random();
        int index = -1;
        for (Governance governance : game.getGovernances()) {
            index++;
            int xCoordinate = COORDINATES[index][0] * 2 + random.nextInt(-4, 5);
            int yCoordinate = COORDINATES[index][1] * 2 + random.nextInt(-4, 5);
            Cell cell = game.getGameMap().getCellByLocation(xCoordinate, yCoordinate);
            cell.setTexture(Texture.GROUND);
            governance.addBuilding(BuildingType.KEEP, cell);
            Cell stockpileCell = game.getGameMap().getCellByLocation(xCoordinate + 6, yCoordinate + 6);
            stockpileCell.setTexture(Texture.GROUND);
            governance.addBuilding(BuildingType.STOCKPILE, stockpileCell);
        }
    }
}
