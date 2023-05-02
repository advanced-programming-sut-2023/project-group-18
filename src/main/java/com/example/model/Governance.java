package com.example.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.model.Assets.Asset;
import com.example.model.Assets.AssetType;
import com.example.model.Buildings.Building;
import com.example.model.Buildings.BuildingType;
import com.example.model.Buildings.Storage;
import com.example.model.People.Soldier;
import com.example.model.People.SoldierType;

public class Governance {
    private final User owner;
    private final ArrayList<Building> buildings;
    private final ArrayList<Soldier> soldiers;
    // private final ArrayList<Person> persons; Not needed
    private final HashMap<AssetType, HashMap<Asset, Integer>> assets;
    private final PopularityFactors popularity;
    private int gold;
    private int nonMilitaryCharacters;

    public Governance(User owner) {
        this.owner = owner;
        buildings = new ArrayList<>();
        soldiers = new ArrayList<>();
        assets = Asset.getAllAssets();
        popularity = new PopularityFactors(this);
        gold = 100;
        nonMilitaryCharacters = 0;
    }

    public User getOwner() {
        return owner;
    }

    public PopularityFactors getPopularity() {
        return popularity;
    }

    public int getGold() {
        return gold;
    }

    public int getNonMilitaryCharacters() {
        return nonMilitaryCharacters;
    }

    public void addNonMilitaryCharacters(int nonMilitaryCharacters) {
        this.nonMilitaryCharacters += nonMilitaryCharacters;
    }


    public void addSpecificAsset(Asset asset, int count) {
        int governanceCount = assets.get(asset.getAssetType()).get(asset);
        assets.get(asset.getAssetType()).put(asset, governanceCount + count);
    }

    public boolean canRemoveAssetFromStorage(Asset asset, int count) {
        int canRemove = 0;
        for (Building building : buildings) {
            if (!(building instanceof Storage)) continue;
            Storage storage = (Storage) building;
            if (!storage.isAssetCompatible(asset)) continue;
            if (storage.isPossibleRemoveProduct(asset, count)) return true;
            canRemove += storage.getAssetCount(asset);
            if (canRemove >= count) return true;
        }
        return false;
    }

    public void addAssetToStorage(Asset asset, int count) {
        for (Building building : buildings) {
            if (!(building instanceof Storage)) continue;
            Storage storage = (Storage) building;
            if (!storage.isAssetCompatible(asset)) continue;
            int canAdd = count > storage.remainingCapacity() ? storage.remainingCapacity() : count;
            storage.addProduct(asset, canAdd);
            count -= canAdd;
            if (count == 0) return;
        }
        return;
    }

    public void removeAssetFromStorage(Asset asset, int count) {
        for (Building building : buildings) {
            if (!(building instanceof Storage)) continue;
            Storage storage = (Storage) building;
            if (!storage.isAssetCompatible(asset)) continue;
            int canRemove = count > storage.getAssetCount(asset) ? storage.getAssetCount(asset) : count;
            storage.addProduct(asset, -canRemove);
            count -= canRemove;
            if (count == 0) return;
        }
    }

    public void addBuilding(BuildingType buildingType, Cell cell) {
        buildings.add(new Building(buildingType, this, cell));
    }

    public void addSoldier(Cell cell, SoldierType soldierType) {
        soldiers.add(new Soldier(cell, this, soldierType));
    }

}
