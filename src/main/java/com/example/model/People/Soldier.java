package com.example.model.People;

import com.example.model.Governance;
import com.example.model.Map.Cell;

public class Soldier extends Unit {
    private final SoldierType soldierType;
    private final int attackPower;
    private final int defencePower;
    private final int speed;
    private final int cost;
    private final int attackRange;

    public Soldier(Cell personCell, Governance governance, SoldierType soldierType) {
        super(personCell, governance);
        this.soldierType = soldierType;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.speed = speed;
        this.cost = cost;
        this.attackRange = attackRange;
    }

    public void attack(Cell cell){
        //Todo
    }

    public void move(Cell cell){
        //Todo
    }
}
