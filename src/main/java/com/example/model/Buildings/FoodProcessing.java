package com.example.model.Buildings;

public class FoodProcessing extends Building{
    private final String type;
    private final String material;
    private final String product;
    private final int rate;

    public FoodProcessing(String buildingType, Governance governance, Cell cell, String type) {
        super(buildingType, governance, cell);
        this.type = type;
    }

    public void process(){

    }
}
