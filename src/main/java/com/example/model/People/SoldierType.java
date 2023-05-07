package com.example.model.People;

import com.example.model.Assets.Asset;

public enum SoldierType {
    ARCHER("Archer", 2, 3,4,12,
            40, Asset.BOW, null,null,true,false),
    CROSS_BOW_MEN("Crossbowmen", 2, 4,2,12,
            20, Asset.CROSSBOW, Asset.LEATHER_ARMOR,null,false,false),
    SPEARMEN("Spearmen", 3, 2,3,12,
            1, Asset.SPEAR, null,null,true,true),
    PIKEMEN("PIKEMEN", 3, 5,2,12,
            1, Asset.PIKE, Asset.METAL_ARMOR,null,false,true),
    MACEMEN("Macemen",4, 4,3,12,
            1, Asset.MACE, Asset.LEATHER_ARMOR,null,true,true),
    SWORDSMEN("Swordsmen", 5, 2,0,12,
            1, Asset.SWORDS, Asset.METAL_ARMOR,null,false,false),
    KNIGHT("Knight", 5, 5,5,12,
            1, Asset.SWORDS, Asset.METAL_ARMOR,"Crusader",false,false),
    TUNNELER("Tunneler", 3, 2,4,12,
            0, Asset.PICK_AXE,null,null,false,false),
    LADDERMEN("Laddermen", 0, 2,4,12,
            0, null, null,null,false,false),
    ENGINEER("Engineer", 0, 2,3,12,
            0, null, null,null,false,true),
    BLACK_MONK("Black Monk", 3, 4,2,12,
            1, Asset.STAFF, null,null,false,false),
    ARCHER_BOW("Archer Bow", 2, 3,4,12,
            40, Asset.BOW, null,null,false,true),
    SLAVE("Slave", 1, 0,1,12,
            1, Asset.TORCH, null,null,false,true),
    SLINGER("Slinger", 2, 2,4,12,
            10, Asset.SLING, null,null,false,false),
    ASSASSIN("Assassin", 3, 4,3,12,
            1, Asset.SCIMITAR,null,null,false,false),
    HORSE_ARCHERS("Horse Archers", 2,4 ,5,12,
            50, Asset.ARABIAN_BOW, null,"Arabian",false,false),
    ARABIAN_SWORDSMEN("Arabian Swordsmen", 5, 0,5,12,
            10, Asset.SCIMITAR, Asset.ARABIAN_METAL_ARMOR,null,false,false),
    FIRE_THROWER("Fire Thrower", 4, 3,5,12,
            10, Asset.GREEK_FIRE, null,null,false,false);
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
    private final String horseType;
    private final boolean canDig;
    private final boolean canClimb;

    SoldierType(String type, int attackPower, int defencePower, int speed, int cost,
                int attackRange, Asset weapon, Asset armor, String horseType, boolean canClimb, boolean canDig) {
        this.type = type;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.speed = speed;
        this.cost = cost;
        this.attackRange = attackRange;
        this.weapon = weapon;
        this.armor = armor;
        this.horseType = horseType;
        this.canClimb = canClimb;
        this.canDig = canDig;
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

    public String getHorseType() {
        return horseType;
    }

    public boolean isCanDig() {
        return canDig;
    }

    public boolean isCanClimb() {
        return canClimb;
    }
}
