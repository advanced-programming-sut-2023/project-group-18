package com.example.model.buildings;

import com.example.model.Governance;
import com.example.model.map.Tile;
import com.example.model.people.Unit;

import java.util.ArrayList;

// need to remove this class
public class Trap extends Building {

    public Trap(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
    }

    // TODO: bring to GameController
    public void run() {
        ArrayList<Unit> enemyUnits = new ArrayList<>();
        for (Unit unit : tiles.get(0).getUnits()){
            if (!unit.getGovernance().equals(governance))
                enemyUnits.add(unit);
        }
        if (enemyUnits.isEmpty())
            return;
        if (this.getHitpoint() != 0){
            for (Unit unit : enemyUnits){
                unit.gotDamage(buildingType.getAttackPoint());
            }
            this.setHitpoint(0);
        }
    }
}
