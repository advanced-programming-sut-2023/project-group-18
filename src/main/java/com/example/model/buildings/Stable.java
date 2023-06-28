package com.example.model.buildings;

import com.example.model.Governance;
import com.example.model.map.Tile;

public class Stable extends Building{
    private final int horsesNumber;
    private int remainingHorses;

    public Stable(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
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
