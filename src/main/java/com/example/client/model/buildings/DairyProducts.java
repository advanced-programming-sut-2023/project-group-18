package com.example.client.model.buildings;

import com.example.client.model.Governance;
import com.example.client.model.map.Tile;

public class DairyProducts extends Farm {
    private int cowNumber;

    public DairyProducts(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
        this.cowNumber = 4;
    }

    public void addCow(int count){
        cowNumber += count;
    }

    public void useCow(){
        cowNumber--;
    }

    public int getCowNumber() {
        return cowNumber;
    }

    public boolean canUseCow(){
        return this.getCowNumber() > 0;
    }
}
