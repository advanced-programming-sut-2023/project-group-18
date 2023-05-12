package com.example.model.People;

import com.example.model.Assets.Asset;
import com.example.model.Buildings.BuildingType;

public enum SoldierType {
    ARCHER("Archer", 2, 3,4,12,
            40, Asset.BOW, null,null,true,false,BuildingType.BARRACKS),
    CROSS_BOW_MEN("Crossbowmen", 2, 4,2,12, 20, Asset.CROSSBOW,
            Asset.LEATHER_ARMOR,null,false,false,BuildingType.BARRACKS),
    SPEARMEN("Spearmen", 3, 2,3,12,
            1, Asset.SPEAR, null,null,true,true ,BuildingType.BARRACKS),
    PIKEMEN("PIKEMEN", 3, 5,2,12, 1, Asset.PIKE, Asset.METAL_ARMOR,
            null,false,true,BuildingType.BARRACKS),
    MACEMEN("Macemen",4, 4,3,12, 1, Asset.MACE,
            Asset.LEATHER_ARMOR,null,true,true, BuildingType.BARRACKS),
    SWORDSMEN("Swordsmen", 5, 2,0,12, 1, Asset.SWORDS,
            Asset.METAL_ARMOR,null,false,false ,BuildingType.BARRACKS),
    KNIGHT("Knight", 5, 5,5,12, 1, Asset.SWORDS,
            Asset.METAL_ARMOR,"Crusader",false,false, BuildingType.BARRACKS),
    TUNNELER("Tunneler", 3, 2,4,12, 0, Asset.PICK_AXE,null,
            null,false,false, BuildingType.ENGINEER_GUILD),
    LADDERMEN("Laddermen", 0, 2,4,12, 0, null,
            null,null,false,false, BuildingType.ENGINEER_GUILD),
    ENGINEER("Engineer", 0, 2,3,12, 0, null,
            null,null,false,true, BuildingType.ENGINEER_GUILD),
    BLACK_MONK("Black Monk", 3, 4,2,12, 1, Asset.STAFF,
            null,null,false,false, BuildingType.CATHEDRAL),
    ARCHER_BOW("Archer Bow", 2, 3,4,12, 40, Asset.BOW,
            null,null,false,true, BuildingType.MERCENARY_POST),
    SLAVE("Slave", 1, 0,1,12, 1, Asset.TORCH, null,
            null,false,true, BuildingType.MERCENARY_POST),
    SLINGER("Slinger", 2, 2,4,12, 10, Asset.SLING,
            null,null,false,false, BuildingType.MERCENARY_POST),
    ASSASSIN("Assassin", 3, 4,3,12, 1, Asset.SCIMITAR,
            null,null,false,false, BuildingType.MERCENARY_POST),
    HORSE_ARCHERS("Horse Archers", 2,4 ,5,12, 50, Asset.ARABIAN_BOW,
            null,"Arabian",false,false, BuildingType.MERCENARY_POST),
    ARABIAN_SWORDSMEN("Arabian Swordsmen", 5, 0,5,12, 10, Asset.SCIMITAR
            ,Asset.ARABIAN_METAL_ARMOR,null,false,false, BuildingType.MERCENARY_POST),
    FIRE_THROWER("Fire Thrower", 4, 3,5,12, 10, Asset.GREEK_FIRE,
            null,null,false,false, BuildingType.MERCENARY_POST);
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
    private final BuildingType buildingType;

    SoldierType(String type, int attackPower, int defencePower, int speed, int cost, int attackRange, Asset weapon,
                Asset armor, String horseType, boolean canClimb, boolean canDig, BuildingType buildingType) {
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
        this.buildingType = buildingType;
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

    public boolean canDig() {
        return canDig;
    }

    public boolean canClimb() {
        return canClimb;
    }

    public boolean isCanDig() {
        return canDig;
    }

    public boolean isCanClimb() {
        return canClimb;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public static SoldierType getSoldierTypeByName(String type){
        for (SoldierType soldierType : SoldierType.values()){
            if (soldierType.getType().equals(type)){
                return soldierType;
            }
        }
        return null;
    }
}
