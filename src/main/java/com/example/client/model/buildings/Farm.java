package com.example.client.model.buildings;

import com.example.client.model.Governance;
import com.example.client.model.assets.Asset;
import com.example.client.model.map.Tile;

public class Farm extends Building {
    private final int rate;
    private final Asset product;

    public Farm(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
        this.rate = buildingType.getProductionRate();
        this.product = buildingType.getProductionAsset();
    }

    public int getRate() {
        return rate;
    }

    public Asset getProduct() {
        return product;
    }


    public void produce(){
        governance.addAssetToStorage(product,rate);
    }

    @Override
    public String toString() {
        String result = "\nRate: " + rate;
        if (product != null) result += "\nProduct: " + product.getName();
        return super.toString() + result;
    }

}
