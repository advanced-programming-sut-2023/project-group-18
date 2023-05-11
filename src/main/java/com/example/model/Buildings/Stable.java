package com.example.model.Buildings;

import com.example.model.Governance;

public class Stable extends Building{
    private final int horsesNumber;
    private int remainingHorses;

    public Stable(BuildingType buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
        this.horsesNumber = 4;
        this.remainingHorses = 4;
    }

    public int getHorsesNumber() {
        return horsesNumber;
    }

    public int getRemainingHorses() {
        return remainingHorses;
    }

    public int getHorsesInUse(){
        return horsesNumber - remainingHorses;
    }

    public void addHorse(){
        remainingHorses ++;
    }

    public void removeHorse(){
        remainingHorses --;
    }
}
