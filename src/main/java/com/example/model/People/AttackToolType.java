package com.example.model.People;

import com.example.model.Assets.Asset;
import com.example.model.Buildings.BuildingType;

public enum AttackToolType {
    SIEGE_TOWER("siege tower",4,0,20,0),
    PORTABLE_SHIELD("portable shield", 1,0,20,0),
    CATAPULT("catapult", 2,50,20,50),
    BATTERING_RAM("battering ram", 4,100,10,1),
    FIRE_BALLISTA("fire ballista",3,50,20,50),
    MANGONEL("mangonel",3,50,0,50),
    ;
    private final String type;
    private final int engineersNeeded;
    private final int attackPower;
    private final int speed;
    private final int attackRange;

    AttackToolType(String type, int engineersNeeded, int attackPower, int speed, int attackRange) {
        this.type = type;
        this.engineersNeeded = engineersNeeded;
        this.attackPower = attackPower;
        this.speed = speed;
        this.attackRange = attackRange;
    }

    public String getType() {
        return type;
    }

    public int getEngineersNeeded() {
        return engineersNeeded;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public AttackToolType getAttackToolTypeByName(String name){
        for (AttackToolType toolType : AttackToolType.values()){
            if (toolType.getType().equals(name))
                return toolType;
        }
        return null;
    }
}
