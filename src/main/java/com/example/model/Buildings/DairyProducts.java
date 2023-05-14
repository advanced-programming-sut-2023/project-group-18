package com.example.model.Buildings;

import com.example.model.Governance;
import com.example.model.Map.Cell;

public class DairyProducts extends Farm{
    private int cowNumber;

    public DairyProducts(BuildingType buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
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
