package com.example.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.model.Buildings.Building;
import com.example.model.Comodities.Asset;
import com.example.model.Comodities.AssetType;
import com.example.model.People.Person;
import com.example.model.People.Soldier;

public class Governance {
    private final User owner;
    private final ArrayList<Building> buildings;
    private final ArrayList<Soldier> soldiers;
    private final ArrayList<Person> persons;
    private final HashMap<AssetType, HashMap<Asset, Integer>> assets;
    private int gold;

    public Governance(User owner) {
        this.owner = owner;
        buildings = new ArrayList<>();
        soldiers = new ArrayList<>();
        persons = new ArrayList<>();
        assets = Asset.getAllAssets();
    }

    public User getOwner() {
        return owner;
    }

    // TODO: getters & setters

    private void addToSpeceficAsset(Asset asset, int count) {
        int governanceCount = assets.get(asset.getAssetType()).get(asset);
        assets.get(asset.getAssetType()).put(asset, governanceCount + count);
    }


}
