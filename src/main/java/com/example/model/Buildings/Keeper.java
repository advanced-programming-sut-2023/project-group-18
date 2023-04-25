package com.example.model.Buildings;

public class Keeper extends Tower{
    private final int populationEffect;

    public Keeper(String buildingType, Governance governance, Cell cell, int fireRange, int defendRange, String type, int populationEffect) {
        super(buildingType, governance, cell, fireRange, defendRange, type);
        this.populationEffect = populationEffect;
    }
}
