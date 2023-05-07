package com.example.model.Buildings;

import com.example.model.Cell;
import com.example.model.Governance;
import com.example.model.People.Soldier;

import java.util.ArrayList;

public class Trap extends Building{
    private final int damage;

    public Trap(BuildingType buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
        this.damage = 100;
    }

    public int getDamage() {
        return damage;
    }


    public void doDamage(){
        //ArrayList<Soldier> enemy
    }
}
