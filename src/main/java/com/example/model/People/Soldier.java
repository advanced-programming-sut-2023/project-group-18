package com.example.model.People;

import com.example.model.Map.Cell;
import com.example.model.Governance;
import com.example.model.Map.Texture;

public class Soldier extends Unit {
    private final SoldierType soldierType;
    private final int attackPower;
    private final int defencePower;
    private final int speed;
    private final int cost;
    private final int attackRange;
    private int damage;

    public Soldier(Cell personCell, Governance governance, SoldierType soldierType) {
        super(governance,UnitType.SOLDIER,personCell);
        this.soldierType = soldierType;
        this.attackPower = soldierType.getAttackPower();
        this.defencePower = soldierType.getDefencePower();
        this.speed = soldierType.getSpeed();
        this.cost = soldierType.getCost();
        this.attackRange = soldierType.getAttackRange();
        this.damage = soldierType.getAttackPower() * (20 + governance.getFearRate());
    }

    public void updateDamage(){
        this.damage = soldierType.getAttackPower() * (20 + this.getGovernance().getFearRate());
    }

    public int getDamage() {
        updateDamage();
        return damage;
    }

    public boolean canDigHole(){
        return this.soldierType.canDig();
    }

    public void digHole(Cell cell){
        cell.setTexture(Texture.HOLE);
    }

    public void removeHole(Cell cell){
        cell.setTexture(Texture.GROUND);
    }

    public void attack(Cell cell){
        Unit enemyUnit = null;
        for (Unit unit : cell.getUnits()){
            if (!unit.getGovernance().equals(this.getGovernance()))
                enemyUnit = unit;
        }
        if (enemyUnit == null)
            return;
        enemyUnit.addHitpoint(-this.getDamage());
    }

}
