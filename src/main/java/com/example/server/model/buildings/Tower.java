package com.example.server.model.buildings;

import com.example.server.model.Governance;
import com.example.server.model.assets.Asset;
import com.example.server.model.map.Tile;

public class Tower extends Building {
    private final int fireRange;
    private final int defendRange;
    private final int soldiersCapacity;
    protected boolean hasLadder;
    private final boolean strong;

    public Tower(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
        this.fireRange = buildingType.getFireRange();
        this.defendRange = buildingType.getDefendRange();
        hasLadder = false;
        soldiersCapacity = buildingType.getSoldiersCapacity();
        if (buildingType.equals(BuildingType.CIRCLE_TOWER) || buildingType.equals(BuildingType.SQUARE_TOWER))
            strong = true;
        else strong = false;
    }

    public int getFireRange() {
        return fireRange;
    }

    public int getDefendRange() {
        return defendRange;
    }

    public int getSoldiersCapacity() {
        return soldiersCapacity;
    }

    public boolean isHasLadder() {
        return hasLadder;
    }

    public void setLadder() {
        this.hasLadder = true;
    }

    public void removeLadder() {
        this.hasLadder = false;
    }


    public boolean isStrong() {
        return strong;
    }

    public boolean canRepair() {
        Asset asset = Asset.STONE;
        int stoneNeeded = this.getBuildingType().getHitpoint() - this.hitpoint;
        return governance.canRemoveAssetFromStorage(asset, stoneNeeded);
    }

    public void repair() {
        Asset asset = Asset.STONE;
        int stoneNeeded = this.getBuildingType().getHitpoint() - this.hitpoint;
        if (governance.canRemoveAssetFromStorage(asset, stoneNeeded)) {
            governance.removeAssetFromStorage(asset, stoneNeeded);
            this.hitpoint = this.getBuildingType().getHitpoint();
        } else {
            this.hitpoint += governance.getAssetCount(asset);
            governance.removeAssetFromStorage(asset, governance.getAssetCount(asset));
        }
    }

    @Override
    public boolean isReachable() {
        return hasLadder;
    }

}
