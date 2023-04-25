package com.example.model.Buildings;

public class Stable extends Building{
    private final int horsesNumber;
    private int remainingHorses;

    public Stable(String buildingType, Governance governance, Cell cell, int horsesNumber, int remainingHorses) {
        super(buildingType, governance, cell);
        this.horsesNumber = horsesNumber;
        this.remainingHorses = remainingHorses;
    }

    public int getHorsesNumber() {
        return horsesNumber;
    }

    public int getRemainingHorses() {
        return remainingHorses;
    }

    public void addHorse(){
        remainingHorses ++;
    }

    public void removeHorse(){
        remainingHorses --;
    }
}
