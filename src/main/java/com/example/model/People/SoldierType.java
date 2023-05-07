package com.example.model.People;

import com.example.model.Assets.Asset;

public enum SoldierType {
    //Todo (add all types of soldiers)
    ;
    private final String type;
    private final int attackPower;
    private final int defencePower;
    private final int speed;
    private final int cost;
    private final int attackRange;
    private final Asset weapon;
    private final Asset armor;

    SoldierType(String type, int attackPower, int defencePower, int speed, int cost, int attackRange, Asset weapon, Asset armor) {
        this.type = type;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.speed = speed;
        this.cost = cost;
        this.attackRange = attackRange;
        this.weapon = weapon;
        this.armor = armor;
    }

    public String getType() {
        return type;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefencePower() {
        return defencePower;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCost() {
        return cost;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public Asset getWeapon() {
        return weapon;
    }

    public Asset getArmor() {
        return armor;
    }
}
