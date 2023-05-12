package com.example.model.Buildings;

import com.example.model.Assets.Asset;
import com.example.model.Governance;
import com.example.model.Map.Cell;

public class Processing extends Building{
    private final Asset material;
    private final Asset product;
    private final int rate;

    public Processing(BuildingType buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
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
}
