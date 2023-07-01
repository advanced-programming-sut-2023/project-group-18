package com.example.model.buildings;

import java.util.HashMap;

import com.example.model.Governance;
import com.example.model.assets.Asset;
import com.example.model.assets.AssetType;
import com.example.model.map.Tile;

public class Storage extends Building {
    private final AssetType type;
    private final HashMap<Asset, Integer> products;
    private int currentCapacity;

    public Storage(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
        this.type = AssetType.getAssetTypeByBuildingType(buildingType);
        products = Asset.getAllAssets(type);
    }

    public AssetType getType() {
        return type;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public int remainingCapacity() {
        return buildingType.getCapacity() - currentCapacity;
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

    // TODO: bring to GameController
    public void addProduct(Asset asset, int count) {
        if (currentCapacity + count > buildingType.getCapacity())
            count -= buildingType.getCapacity() - currentCapacity;
        currentCapacity += count;
        products.put(asset, products.get(asset) + count);
        governance.addSpecificAsset(asset, count);
    }

    @Override
    public String toString() {
        String result = " Storage: ";
        for (Asset asset : products.keySet())
            result += "\n" + asset.getName() + ": " + products.get(asset);
        result += "\nTotal: " + currentCapacity + "/" + buildingType.getCapacity();
        return super.toString() + result;
    }

}
