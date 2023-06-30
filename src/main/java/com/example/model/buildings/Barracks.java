package com.example.model.buildings;

import java.util.Random;

import com.example.model.Governance;
import com.example.model.assets.Asset;
import com.example.model.map.Tile;
import com.example.model.people.Unit;
import com.example.model.people.UnitType;

public class Barracks extends Building{
    public Barracks(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
    }

    public boolean canMakeSoldier(UnitType unitType) {
        // if (governance.getRemainingNonMilitary() == 0) return false;
        // if (governance.getGold() < unitType.getCost()) return false;
        // for (Asset asset : unitType.getAssets())
        //     if (governance.canRemoveAssetFromStorage(asset,1))
        //         return false;
        return true;
    }

    public void makeUnit(UnitType unitType) {
        for (Asset asset : unitType.getAssets())
            governance.removeAssetFromStorage(asset,1);
        governance.addGold(-unitType.getCost());
        governance.removeRemainingCharacter();
        int yIndex = tiles.get(0).getGameMap().getTileYIndex(tiles.get(0).getPoint2d().getY());
        int xIndex = tiles.get(0).getGameMap().getTileXIndex(tiles.get(0).getPoint2d().getX(), goldCost);
        xIndex = getIndex(xIndex);
        yIndex = getIndex(yIndex);
        governance.getUnits().add(new Unit(governance, unitType, tiles.get(0).getGameMap().getTileByIndex(xIndex, yIndex)));
    }
    
    private int getIndex(int index) {
        Random random = new Random();
        index += random.nextInt(2, 5);
        if (random.nextBoolean()) index *= -1;
        return index < 0 ? 0 : index;
    }

    @Override
    public boolean isReachable() {
        return false;
    }
}
