package com.example.model.Buildings;

import com.example.model.Assets.Asset;

import java.util.ArrayList;
import java.util.HashMap;

public class Armory extends Building{
    private final String weapon;
    private final ArrayList<String> resources;
    private final int rate;

    public Armory(BuildingType buildingType, G governance, Cell cell, String weapon) {
        super(buildingType, governance, cell);
        this.weapon = weapon;
    }
    public void canMakeWeapon(String weapon){
        if ()
    }
    public void makeWeapon(String weapon){
        Asset weaponAsset = Asset.getAssetByName(weapon);
        governance.addGold(- weaponAsset.getPrice());
        governance.removeAssetFromStorage();
        governance.addSpecificAsset(weaponAsset,1);
    }
}
