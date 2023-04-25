package com.example.model.Buildings;

import com.example.model.Cell;
import com.example.model.Governance;

public class House extends Building{
    private final int populationEffect;
    public House(String buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
        populationEffect = buildingType.populationEffect;
        governance.addPopulation(populationEffect);
    }

    public int getPopulationEffect() {
        return populationEffect;
    }
}
