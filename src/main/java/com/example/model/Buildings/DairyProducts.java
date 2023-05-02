package com.example.model.Buildings;

public class DairyProducts extends Farm{
    private int cowNumber;

    public DairyProducts(String buildingType, Governance governance, Cell cell, int cowNumber) {
        super(buildingType, governance, cell);
        this.cowNumber = cowNumber;
    }

    private void addCow(){
        cowNumber++;
    }

    private void useCow(){
        cowNumber--;
    }

    public int getCowNumber() {
        return cowNumber;
    }
}
