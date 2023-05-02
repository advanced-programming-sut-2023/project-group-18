package com.example.model.Buildings;

public class IndustrialBuilding extends Building{
    private final int rate;
    private final String type;
    private final int capacity;
    private final int remainingCapacity;

    public IndustrialBuilding(String buildingType, Governance governance, Cell cell, int rate, String type, int capacity, int remainingCapacity) {
        super(buildingType, governance, cell);
        this.rate = rate;
        this.type = type;
        this.capacity = capacity;
        this.remainingCapacity = remainingCapacity;
    }

    public int getRate() {
        return rate;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    public void produce(){
        //Todo
    }

}
