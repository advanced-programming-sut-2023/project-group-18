package com.example.model.Buildings;

public class Gate extends Tower{
    private boolean isOpen;
    private boolean hasBridge;

    public Gate(String buildingType, Governance governance, Cell cell, int fireRange, int defendRange, String type) {
        super(buildingType, governance, cell, fireRange, defendRange, type);
        isOpen = false;
        hasBridge = false;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean hasBridge() {
        return hasBridge;
    }

    public void close(){
        isOpen = false;
    }

    public void open(){
        isOpen = true;
    }

    public void makeBridge(){
        hasBridge = true;
    }
}
