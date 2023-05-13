package com.example.model.People;

import com.example.model.Assets.Asset;
import com.example.model.Buildings.BuildingType;

public enum UnitType {
    LORD("Lord", 5, 1, BuildingType.KEEP,true,null,null,
            0),
    WOOD_CUTTERS("Woodcutters", 5, 1, BuildingType.WOOD_CUTTER,false,null,
            Asset.WOOD, 15),
    HUNTERS("Hunters", 5, 1, BuildingType.HUNT_POST,false,null,Asset.MEAT,
            5),
    APPLE_FARMER("Apple farmer", 5, 1, BuildingType.APPLE_FARM,false,null,
            Asset.APPLE, 5),
    WHEAT_FARMER("Wheat farmer", 5, 1, BuildingType.WHEAT_FARM,false,null,
            Asset.WHEAT, 5),
    HOPS_FARMER("Hops farmer", 5, 1, BuildingType.HOP_FARM,false,null,
            Asset.HOPS, 5),
    DAIRY_FARMER("Dairy Farmer", 5, 1,BuildingType.DAIRY_PRODUCTS, false,
            null, Asset.CHEESE, 5),
    SOLDIER("Soldier", 0, 0,null,true,null,null,
            0),
    PEASANTS("Peasants", 0,1,BuildingType.KEEP,false,null,null,
            0),
    CHILDREN("Children", 5, 1, BuildingType.HOVEL,false,null,null,
            0),
    MOTHERS_AND_BABIES("Mothers and Babies", 5, 1, BuildingType.HOVEL, false,
            null, null,0),
    STONE_MASONS("Stone Masons", 5, 1, BuildingType.QUARRY,false,null,
            null, 0),
    IRON_MINERS("Iron Miners", 5, 1, BuildingType.IRON_MINE,false,null
            ,Asset.IRON, 3),
    PITCH_DIGGERS("Pitch Diggers", 5, 1, BuildingType.PITCH_DITCH,false,null,
            Asset.PITCH, 1),
    MILL_BOYS("Mill Boys", 5, 1, BuildingType.MILL,false,Asset.WHEAT,Asset.FLOUR,
            5),
    BAKER("Baker", 5, 1, BuildingType.BAKERY,false,Asset.FLOUR,Asset.BREAD,
            5),
    BREWER("Brewer", 5, 1, BuildingType.BREWERY,false,Asset.HOPS,Asset.ALE,
            1),
    INNKEEPER("Innkeeper", 5, 1, BuildingType.INN,false,Asset.ALE,null,
            0),
    FLETCHER("Fletcher", 5, 1, BuildingType.FLETCHER,false,Asset.WOOD,Asset.BOW,
            1),
    ARMORER("Armorer", 5, 1, BuildingType.ARMOURER,false,Asset.IRON
            ,Asset.METAL_ARMOR, 0),
    BLACKSMITH("Blacksmith", 5, 1, BuildingType.BLACKSMITH_MACE,false,null,
            null, 1),
    POLETURNER("Poleturner", 5, 1, BuildingType.POLETURNER,false,Asset.WOOD,
            Asset.PIKE, 1),
    TANNER("Tanner", 5, 1, null,false,null,null,
            0),
    ENGINEER("Tanner", 5, 1, null,false,null,null,
            0),
    ATTACK_TOOL("Attack Tool",50,1000,null,true,null,
            null,0),
    ;

    private final String name;
    private final int maxSpeed;
    private final int maxHitpoint;
    private final BuildingType buildingType;
    private final boolean controllable;
    private final Asset material;
    private final Asset product;
    private final int productCount;

    UnitType(String name, int maxSpeed, int maxHitpoint, BuildingType buildingType, boolean controllable,
             Asset material, Asset product, int productCount) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.maxHitpoint = maxHitpoint;
        this.buildingType = buildingType;
        this.controllable = controllable;
        this.material = material;
        this.product = product;
        this.productCount = productCount;
    }

    public static UnitType getUnitTypeByName(String name) {
        for (UnitType unitType : UnitType.values())
            if (unitType.name.equals(name)) return unitType;
        return null;
    }

    public static UnitType getUnitTypeByBuildingType(BuildingType buildingType){
        for (UnitType unitType : UnitType.values()){
            if (unitType.buildingType.equals(buildingType))
                return unitType;
        }
        return null;
    }
    public String getName() {
        return name;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxHitpoint() {
        return maxHitpoint;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public boolean isControllable() {
        return controllable;
    }

    public Asset getMaterial() {
        return material;
    }

    public Asset getProduct() {
        return product;
    }

    public int getProductCount() {
        return productCount;
    }
}
