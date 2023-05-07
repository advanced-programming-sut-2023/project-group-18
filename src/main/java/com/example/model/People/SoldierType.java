package com.example.model.People;

import com.example.model.Assets.Asset;

public enum SoldierType {
    ARCHER("Archer", 2, 3,4,12, 40, Asset.BOW, null),
    CROSS_BOW_MEN("Crossbowmen", 2, 4,2,12, 20, Asset.CROSSBOW, Asset.LEATHER_ARMOR),
    SPEARMEN("Spearmen", 3, 2,3,12,1, Asset.SPEAR, null),
    PIKEMEN("PIKEMEN", 3, 5,2,12, 1, Asset.PIKE, Asset.METAL_ARMOR),
    MACEMEN("Macemen",4, 4,3,12, 1, Asset.MACE, Asset.LEATHER_ARMOR),
    SWORDSMEN("Swordsmen", 5, 2,0,12, 1, Asset.SWORDS, Asset.METAL_ARMOR),
    KNIGHT("Knight", 5, 5,5,12, 1, Asset.SWORDS, Asset.METAL_ARMOR),
    TUNNELER("Tunneler", 3, 2,4,12, 0, Asset.p, null),
    LADDERMEN("Laddermen", 0, 2,4,12, 0, null, null),
    ENGINEER("Engineer", 0, 2,3,12, 0, null, null),
    BLACK_MONK("Black Monk", 3, 4,2,12, 1, Asset.STAFF, null),
    ARCHER_BOW("Archer Bow", 2, 3,4,12, 40, null, null),
    SLAVE("Slave", 1, 0,1,12, 1, null, null),
    SLINGER("Slinger", 2, 2,4,12, 10, null, null),
    ASSASSIN("Assassin", 3, 4,3,12, 1, null, null),
    HORSE_ARCHERS("Horse Archers", 2,4 ,5,12, 50, null, null),
    ARABIAN_SWORDSMEN("Arabian Swordsmen", 5, 0,5,12, 10, null, null),
    FIRE_THROWER("Fire Thrower", 4, 3,5,12, 10, null, null);
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

    SoldierType(String type, int attackPower, int defencePower, int speed,
                int cost, int attackRange, Asset weapon, Asset armor) {
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
