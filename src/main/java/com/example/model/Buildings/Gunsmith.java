package com.example.model.Buildings;

import com.example.model.Assets.Asset;
import com.example.model.Cell;
import com.example.model.Governance;

import java.util.ArrayList;
import java.util.HashMap;

public class Armory extends Building{
    private final Asset weapon;
    private final Asset resource;
    private final int rate;

    public Armory(BuildingType buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
        this.weapon = buildingType.getProductionAsset();
        this.resource = buildingType.getResourceType();
        this.rate = buildingType.getProductionRate();
    }
    public boolean canMakeWeapon(Asset weapon){
        if ()
        return this.weapon == weapon && governance.canRemoveAssetFromStorage(weapon, 1);
    }
    public void makeWeapon(){
        governance.addGold(- this.resource.getPrice());
        governance.removeAssetFromStorage(resource,1);
        governance.addSpecificAsset(weapon,1);
    }

    public void changeMode(){
        //Todo
    }
}
