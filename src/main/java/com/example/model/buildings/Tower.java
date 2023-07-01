package com.example.model.buildings;

import com.example.model.Governance;
import com.example.model.assets.Asset;
import com.example.model.map.Tile;

public class Tower extends Building {

    public Tower(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
    }

    // TODO: bring to GameController
    public boolean canRepair() {
        return governance.canRemoveAssetFromStorage(Asset.STONE, buildingType.getResourceCost());
    }

    // TODO: bring to GameController
    public void repair() {
        governance.removeAssetFromStorage(Asset.STONE, buildingType.getResourceCost());
        this.hitpoint = this.getBuildingType().getHitpoint();
    }

}
