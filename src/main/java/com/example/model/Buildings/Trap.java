package com.example.model.Buildings;

public class Trap extends Building{
    private final int damage;

    public Trap(String buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
    }

    public int getDamage() {
        return damage;
    }


}
