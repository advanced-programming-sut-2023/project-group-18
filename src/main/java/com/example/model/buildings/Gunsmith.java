package com.example.model.buildings;

import com.example.model.Governance;
import com.example.model.assets.Asset;
import com.example.model.map.Tile;

public class Gunsmith extends Building {
    private Asset weapon;
    private final Asset resource;
    private final int rate;

    public Gunsmith(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
        this.weapon = buildingType.getProductionAsset();
        this.resource = buildingType.getResourceType();
        this.rate = buildingType.getProductionRate();
    }
    @Override
    public boolean canWork(){
        return governance.canRemoveAssetFromStorage(this.getBuildingType().getResourceType(),rate) && super.canWork();
    }

    public boolean canMakeWeapon() {
        return governance.canRemoveAssetFromStorage(resource, rate);
    }

    public void makeWeapon(){
        governance.removeAssetFromStorage(resource,rate);
        governance.addSpecificAsset(weapon,rate);
    }

    public void changeMode(){
        this.weapon = BuildingType.getAnotherWeapon(this.getBuildingType(), weapon);
    }

    public void run(){
        if (this.weapon.equals(Asset.LEATHER_ARMOR)){
            for (Building building : governance.getBuildings()){
                if (building.getBuildingType().equals(BuildingType.DAIRY_PRODUCTS)){
                    DairyProducts dairyProducts = (DairyProducts) building;
                    if (dairyProducts.canUseCow()){
                        dairyProducts.useCow();
                        governance.addSpecificAsset(weapon,rate);
                        return;
                    }
                }
            }
        }
        makeWeapon();
    }

    @Override
    public String toString() {
        return super.toString() + "\nWeapon: " + weapon.getName() + "\nRate: " + rate;
    }

    public Asset getWeapon() {
        return weapon;
    }
}
