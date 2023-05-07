package com.example.model.Buildings;

import com.example.model.Assets.Asset;
import com.example.model.Cell;
import com.example.model.Governance;

public class Gunsmith extends Building{
    private Asset weapon;
    private final Asset resource;
    private final int rate;

    public Gunsmith(BuildingType buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
        this.weapon = buildingType.getProductionAsset();
        this.resource = buildingType.getResourceType();
        this.rate = buildingType.getProductionRate();
    }
    @Override
    public boolean canWork(){
        return governance.canRemoveAssetFromStorage(this.getBuildingType().getResourceType(),rate) && super.canWork();
    }
    public void makeWeapon(){
        governance.removeAssetFromStorage(resource,rate);
        governance.addSpecificAsset(weapon,rate);
    }

    public void changeMode(){
        this.weapon = BuildingType.getAnotherWeapon(this.getBuildingType(), weapon);
    }

}
