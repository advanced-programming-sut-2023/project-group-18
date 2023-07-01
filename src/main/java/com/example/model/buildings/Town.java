package com.example.model.buildings;

import com.example.model.Governance;
import com.example.model.map.Tile;

// need to remove this class
public class Town extends Building {

    protected Town(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
        governance.getPopularityFactors().addReligiousFactor(buildingType.getPopularityEffect());
    }

    @Override
    public String toString() {
        return super.toString() + "\nReligious Factor: " + buildingType.getPopularityEffect();
    }
    
}
