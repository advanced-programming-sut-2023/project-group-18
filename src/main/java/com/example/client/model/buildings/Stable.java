package com.example.client.model.buildings;

import com.example.client.model.Governance;
import com.example.client.model.map.Tile;

public class Stable extends Building {
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

    @Override
    public String toString() {
        return super.toString() + "\nHorses: " + remainingHorses + "/" + horsesNumber;
    }
}
