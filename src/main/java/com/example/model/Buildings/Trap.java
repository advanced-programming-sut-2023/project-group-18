package com.example.model.Buildings;

import com.example.model.Governance;
import com.example.model.Map.Cell;
import com.example.model.People.Unit;

import java.util.ArrayList;

public class Trap extends Building{
    private final int damage;

    public Trap(BuildingType buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
        this.damage = 1000;
    }

    public int getDamage() {
        return damage;
    }


    public void run(){
        ArrayList<Unit> enemyUnits = new ArrayList<>();
        for (Unit unit : cell.getUnits()){
            if (!unit.getGovernance().equals(governance))
                enemyUnits.add(unit);
        }
        if (enemyUnits.isEmpty())
            return;
        if (this.getHitpoint() != 0){
            for (Unit unit : enemyUnits){
                unit.doDamage(damage);
            }
            this.setHitpoint(0);
        }
    }
}
