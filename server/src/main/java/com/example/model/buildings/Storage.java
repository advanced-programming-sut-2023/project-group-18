package com.example.model.buildings;

import java.util.HashMap;

import com.example.model.Governance;
import com.example.model.assets.Asset;
import com.example.model.assets.AssetType;
import com.example.model.map.Tile;

public class Storage extends Building {
    private final AssetType type;
    private final int capacity;
    private final HashMap<Asset, Integer> products;
    private int currentCapacity;

    public Storage(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
        this.type = dropStorage(buildingType);
        products = Asset.getAllAssets(type);
        this.capacity = buildingType.getCapacity();
    }

    public AssetType getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public int remainingCapacity() {
        return capacity - currentCapacity;
    }

    public int getAssetCount(Asset asset) {
        return products.get(asset);
    }

    public boolean isPossibleAddProduct(int count) {
        return count <= remainingCapacity();
    }

    public boolean isPossibleRemoveProduct(Asset asset, int count) {
        return products.get(asset) >= count;
    }

    public boolean isAssetCompatible(Asset asset) {
        return asset.getAssetType().equals(type);
    }

    public void addProduct(Asset asset, int count) {
        if (currentCapacity + count > capacity)
            count -= capacity - currentCapacity;
        currentCapacity += count;
        products.put(asset, products.get(asset) + count);
        governance.addSpecificAsset(asset, count);
    }

    private AssetType dropStorage(BuildingType buildingType) {
        switch (buildingType) {
            case STOCKPILE:
                return AssetType.RESOURCE;
            case ARMOURY:
                return AssetType.WEAPON;
            case FOOD_STORAGE:
                return AssetType.FOOD;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        String result = " Storage: ";
        for (Asset asset : products.keySet())
            result += "\n" + asset.getName() + ": " + products.get(asset);
        result += "\nTotal: " + currentCapacity + "/" + capacity;
        return super.toString() + result;
    }

}
