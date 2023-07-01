package com.example.server.model.buildings;

import com.example.server.model.Governance;
import com.example.server.model.map.Tile;
import com.example.server.model.people.Unit;

import java.util.ArrayList;

public class Trap extends Building{
    private final int damage;

    public Trap(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
        this.damage = 1000;
    }

    public int getDamage() {
        return damage;
    }


    public void run(){
        ArrayList<Unit> enemyUnits = new ArrayList<>();
        for (Unit unit : tiles.get(0).getUnits()){
            if (!unit.getGovernance().equals(governance))
                enemyUnits.add(unit);
        }
        if (enemyUnits.isEmpty())
            return;
        if (this.getHitpoint() != 0){
            for (Unit unit : enemyUnits){
                unit.gotDamage(damage);
            }
            this.setHitpoint(0);
        }
    }
}
