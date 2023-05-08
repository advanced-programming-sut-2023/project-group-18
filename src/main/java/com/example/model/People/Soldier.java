package com.example.model.People;

import com.example.model.Cell;
import com.example.model.Governance;
import com.example.model.Map.Texture;

import java.util.Map;

public class Soldier extends Unit {
    private final SoldierType soldierType;
    private final int attackPower;
    private final int defencePower;
    private final int speed;
    private final int cost;
    private final int attackRange;

    public Soldier(Cell personCell, Governance governance, SoldierType soldierType) {
        super(governance,UnitType.SOLDIER,personCell);
        this.soldierType = soldierType;
        this.attackPower = soldierType.getAttackPower();
        this.defencePower = soldierType.getDefencePower();
        this.speed = soldierType.getSpeed();
        this.cost = soldierType.getCost();
        this.attackRange = soldierType.getAttackRange();
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
        //Todo
    }

    public void move(Cell cell){
        //Todo
    }
}
