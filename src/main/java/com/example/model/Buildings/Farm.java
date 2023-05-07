package com.example.model.Buildings;

import com.example.model.Assets.Asset;
import com.example.model.Cell;
import com.example.model.Governance;

public class Farm extends Building{
    private final int rate;
    private final Asset product;

    public Farm(BuildingType buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
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
}
