package com.example.model.people;

import com.example.model.Governance;
import com.example.model.assets.Asset;
import com.example.model.map.Cell;

public class Worker extends Unit{
    private final Asset material;
    private final Asset product;
    private final int productCount;
    public Worker(Governance governance, UnitType unitType, Cell unitCell) {
        super(governance, unitType, unitCell);
        this.material = unitType.getMaterial();
        this.product = unitType.getProduct();
        this.productCount = unitType.getProductCount();
    }

    public Asset getMaterial() {
        return material;
    }

    public Asset getProduct() {
        return product;
    }

    public int getProductCount() {
        return productCount;
    }

    public void run(){

    }
}
