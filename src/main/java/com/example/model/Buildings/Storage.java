package com.example.model.Buildings;

import java.util.HashMap;

import com.example.model.Governance;
import com.example.model.Assets.Asset;
import com.example.model.Assets.AssetType;
import com.example.model.Map.Cell;

public class Storage extends Building {
    private final StorageType storageType;
    private final AssetType type;
    private final int capacity;
    private final HashMap<Asset, Integer> products;
    private int currentCapacity;

    public Storage(BuildingType buildingType, Governance governance, Cell cell, AssetType type) {
        super(buildingType, governance, cell);
        products = Asset.getAllAssets(type);
        this.type = type;
        this.capacity = buildingType.getCapacity();
        this.storageType = type.getStorageType();
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

}
