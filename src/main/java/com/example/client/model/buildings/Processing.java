package com.example.client.model.buildings;

import com.example.client.model.Governance;
import com.example.client.model.assets.Asset;
import com.example.client.model.map.Tile;

public class Processing extends Building {
    private final Asset material;
    private final Asset product;
    private final int rate;

    public Processing(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
        this.material = buildingType.getResourceType();
        this.product = buildingType.getProductionAsset();
        this.rate = buildingType.getProductionRate();
    }

    @Override
    public boolean canWork(){
        if (super.canWork())
            return governance.canRemoveAssetFromStorage(material,rate);
        return false;
    }

    public void produce(){
        governance.addAssetToStorage(product,rate);
        governance.removeAssetFromStorage(material,rate);
    }

    public void run(){
        if (this.canWork()){
            produce();
            governance.getPopularityFactors().addAleCoverage(0.5);
        }
        else governance.getPopularityFactors().addAleCoverage(-0.5);
    }

    @Override
    public String toString() {
        String result = "\n";
        if (material != null) result += "Material: " + material.getName();
        result += "\nRate: " + rate;
        if (product != null) result += "\nProduct: " + product.getName();
        return super.toString() + result;
    }

}
