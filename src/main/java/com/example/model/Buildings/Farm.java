package com.example.model.Buildings;

public class Farm extends Building{
    private final int rate;
    private final String product;
    private int workingPeople;

    public Farm(String buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
    }

    public int getRate() {
        return rate;
    }

    public String getProduct() {
        return product;
    }


    public void produce(){

    }
}
