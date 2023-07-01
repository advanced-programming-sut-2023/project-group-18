package com.example.model.buildings;

import com.example.model.Governance;
import com.example.model.assets.Asset;
import com.example.model.map.Tile;

public class Gunsmith extends Building {
    private final Asset weapon;
    private final Asset resource;
    private final int rate;

    public Gunsmith(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
        this.weapon = buildingType.getProductionAsset();
        this.resource = buildingType.getResourceType();
        this.rate = buildingType.getProductionRate();
    }

    // TODO: bring to GameController
    public boolean canMakeWeapon() {
        return governance.canRemoveAssetFromStorage(resource, rate);
    }

    // TODO: bring to GameController
    public void makeWeapon() {
        governance.removeAssetFromStorage(resource,rate);
        governance.addSpecificAsset(weapon,rate);
    }

    @Override
    public String toString() {
        return super.toString() + "\nWeapon: " + weapon.getName() + "\nRate: " + rate;
    }

    public Asset getWeapon() {
        return weapon;
    }
}
