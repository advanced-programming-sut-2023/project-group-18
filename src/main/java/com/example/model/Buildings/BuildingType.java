package com.example.model.Buildings;

import com.example.controller.Methods.*;
import com.example.model.Assets.Asset;
import com.example.model.Map.Texture;

import java.util.ArrayList;


public enum BuildingType {
    SMALL_STONE_GATEHOUSE("small stone gatehouse",0,Asset.STONE,0, 0,
            Texture.GROUND,100,0,0,0, 8,0, 0,
            20,10,35,null,null,2, Category.GATE,
            GateMethods.getGateMethods()),
    BIG_STONE_GATEHOUSE("big stone gatehouse",0,Asset.STONE,20, 0,Texture.GROUND,
            1,1,1,1, 1,1, 1,1,
            1,1,null,null,3, Category.GATE,
            GateMethods.getGateMethods()),
    DRAWBRIDGE("Drawbridge",0,Asset.WOOD,10,0, Texture.POOL,0,0,
            0,0,0, 0,0,0,0,0,
            null,null,1,Category.BUILDING, null),
    LOOKOUT_TOWER("lookout tower",0,Asset.STONE,10,0,null,300,
            0,0,0,0,0,0,
            40,10,30,null,null,1,Category.TOWER,
            TowerMethods.getTowerMethods()),
    PERIMETER_TOWER("perimeter tower",0,Asset.STONE,10, 0,Texture.GROUND,
            250,0,0,0, 0,0,0,40,
            10,40,null,null,1, Category.TOWER,
            TowerMethods.getTowerMethods()),
    TURRET("turret",0,Asset.STONE,15,0, Texture.GROUND,200,0,
            0,0,0, 0,0, 50,20,
            40,null,null,2, Category.TOWER, TowerMethods.getTowerMethods()),
    SQUARE_TOWER("square tower",0,Asset.STONE,35,0, Texture.GROUND,200,
            0,0,0,0, 0,0,60,20,
            50,null,null,2, Category.TOWER, TowerMethods.getTowerMethods()),
    CIRCLE_TOWER("circle tower",0,Asset.STONE,40,0, Texture.GROUND,200,
            0,0,0,0, 0,0,60,20,
            100,null,null,2, Category.TOWER, TowerMethods.getTowerMethods()),
    ARMOURY("armoury",0,Asset.WOOD,5,0,
            Texture.GROUND,50,50,0,0,0, 0,0,
            0,0,0,null,null,2, Category.STORAGE,
            StorageMethods.getStorageMethods()),
    BARRACKS("barracks",0,Asset.STONE,15,0, Texture.GROUND,50,0,
            0,0,0, 0,0,0,0,
            0,null,null,3, Category.BARRACKS
            ,BarracksMethods.getBarracksMethods()),
    MERCENARY_POST("mercenary post",0,Asset.WOOD,10,0,
            Texture.GROUND,50,0,0,0,0, 0,0,
            0,0,0,null,null,3, Category.BARRACKS,
            BarracksMethods.getBarracksMethods()),
    ENGINEER_GUILD("engineer guild",100,Asset.WOOD,10,0,
            Texture.GROUND,50,0,0,0,0, 0,0,
            0,0,0,null,null,2, Category.BARRACKS,
            BarracksMethods.getBarracksMethods()),
    KILLING_PIT("killing pit",0,Asset.WOOD,6,0,
            Texture.GROUND,0,0,0,0,0, 0,0,
            0,0,0,null,null,1, Category.BUILDING,
            null),
    INN("inn",100,Asset.WOOD,20,1, Texture.GROUND,60,0,0,
            4,0,5,2,0,0,0, Asset.ALE,
            null,2, Category.PROCESSING,null),
    MILL("mill",0,Asset.WOOD,20,3,
            Texture.GROUND,200,0,0,0,0, 5,5,
            0,0,0,Asset.WHEAT,Asset.FLOUR,1, Category.PROCESSING
            ,null),
    IRON_MINE("iron mine",0,Asset.WOOD,20,2, Texture.IRON,10,0,
            0,0,0, 5,0,0,0,0,
            null,Asset.IRON,1,Category.INDUSTRIAL_BUILDING,null),
    MARKET("market",0,Asset.WOOD,5,1, Texture.GROUND,30,0,
            0,0,0, 0,0,0,0,0,
            null,null,2, Category.BUILDING, MarketMethods.getMarketMethods()),
    OX_TETHER("ox tether",0,Asset.WOOD,5,1, Texture.GROUND,5,0,
            0,0,0, 0,0, 0,0,
            0,null,null,1, Category.INDUSTRIAL_BUILDING
            , null),
    PITCH_RIG("pitch rig",0,Asset.WOOD,20,1, Texture.PLAIN,20,0,
            0,0,0, 5,0,0,0,0,
            null,null,1, Category.BUILDING, null),
    QUARRY("quarry",0,Asset.WOOD,20,3, Texture.STONE, 1,30,
            0,0,0, 10,0,0,0,
            0,null,Asset.STONE,2, Category.INDUSTRIAL_BUILDING, null),
    STOCKPILE("stockpile",0,null,0,0, Texture.GROUND,50,
            100,0,0,0, 0,0,0,0,
            0,null,null,2,Category.STORAGE
            , StorageMethods.getStorageMethods()),
    WOOD_CUTTER("wood cutter",0,Asset.WOOD,3,1, Texture.GROUND,20,0,
            0,0,0, 10,0,0,0,
            0,null,Asset.WOOD,1, Category.INDUSTRIAL_BUILDING
            , null),
    HOVEL("hovel",0,Asset.WOOD,6,0, Texture.GROUND,50,0,0,
            0,6, 0,0,0,0,0,
            null,null,2,Category.BUILDING, null),
    CHURCH("church",250,null,0,0, Texture.GROUND,100,0,
            0,2,0, 0,0,0,0,
            0,null,null,3,Category.BUILDING, null),
    CATHEDRAL("cathedral",1000,null,0,0, Texture.GROUND,120,
            0,0,2,0, 0,0,0,0,
            0,null,null,4,Category.BARRACKS
            , BarracksMethods.getBarracksMethods()),
    ARMOURER("armourer",100,Asset.WOOD,20,1, Texture.GROUND,50,0,
            0,0,0, 2,2,0,0,0,
            Asset.IRON, Asset.METAL_ARMOR,2, Category.GUNSMITH,
            null),
    BLACKSMITH_MACE("blacksmith",100,Asset.WOOD,20,1,
            Texture.GROUND,50,0,0,0,0, 2,2,
            0,0,0,Asset.IRON,Asset.MACE,2, Category.GUNSMITH,
            GunsmithMethods.getGunsmithMethods()),
    BLACKSMITH_SWORD("blacksmith",100,Asset.WOOD,20,1,
            Texture.GROUND,50,0,0,0,0, 2,2,
            0,0,0,Asset.IRON,Asset.SWORDS,2, Category.GUNSMITH,
            GunsmithMethods.getGunsmithMethods()),
    FLETCHER("fletcher",100,Asset.WOOD,20,1, Texture.GROUND,50,0,
            0,0,0, 2,2,0,0,
            0,Asset.IRON,Asset.MACE,2, Category.GUNSMITH, GunsmithMethods.getGunsmithMethods()),
    POLETURNER("poleturner",100,Asset.WOOD,20,1,
            Texture.GROUND,50,0,0,0,0, 2,2,
            0,0,0,Asset.IRON,Asset.MACE,2, Category.GUNSMITH,
            GunsmithMethods.getGunsmithMethods()),
    OIL_SMELTER("oil smelter",100,Asset.IRON,10,1, Texture.PETROLEUM,20,
            0,0,0,0, 0,0,0,0,
            0,null,Asset.PITCH,1, Category.INDUSTRIAL_BUILDING
            , null),
    PITCH_DITCH("pitch ditch",0,Asset.PITCH,2,0, Texture.GROUND,0,0,
            0,0,0, 0,0,0,0,0,
            null,null,1, Category.BUILDING, null),
    CAGED_WAR_DOGS("caged war dogs",100,Asset.WOOD,10,0, Texture.GROUND,50,
            0,0,0,0, 0,0,0,0,
            0,null,null,2, Category.BUILDING,null),
    SIEGE_TENT("siege tent",0,null,0, 1,Texture.GROUND,50,
            0,0,0, 0,0, 0,0,0,
            0,null,null,1,Category.BUILDING, null),
    STABLE("stable",400,Asset.WOOD,20,0, Texture.GROUND,50,0,
            0,0,0, 0,0,0,0,
            0,null,null,3,Category.STABLE, null),
    APPLE_FARM("apple farm",0,Asset.WOOD,5,1, Texture.OVERGROWN_GRASSLAND,
            10,0,0,0,0, 5,0,0,
            0,0,null,Asset.APPLE,4,Category.FARM,
            null),
    DAIRY_PRODUCTS("dairy products",0,Asset.WOOD,10,1, Texture.OVERGROWN_GRASSLAND,
            10,0,0,0,0, 5,0,0,
            0,0,null, Asset.CHEESE,4,Category.DAIRY_PRODUCTS,
            null),
    HOP_FARM("hop farm",0,Asset.WOOD,15,1, Texture.OVERGROWN_GRASSLAND,10,
            0,0,0,0, 5,0,0,0,
            0,null,Asset.HOPS,4,Category.FARM, null),
    HUNT_POST("hunt post",0,Asset.WOOD,10,1, Texture.GROUND,30,0,
            0,0,0, 5,0,0,0,0,
            null,Asset.MEAT,1,Category.FARM, null),
    WHEAT_FARM("wheat farm",0,Asset.WOOD,10,1, Texture.OVERGROWN_GRASSLAND,
            10,0,0,0, 0,5,0,0,
            0,0,null,Asset.WHEAT,4,Category.FARM, null),
    BAKERY("bakery",0, Asset.WOOD,10,1, Texture.GROUND,50,0,
            0,0,0, 5,1,0,0,0,
            Asset.FLOUR,Asset.BREAD,2, Category.PROCESSING, null),
    BREWERY("brewery",0,Asset.WOOD,10,1, Texture.GROUND,50,0,
            0,0,0, 1,1,0,0,
            0,Asset.HOPS,Asset.ALE,2,Category.PROCESSING, null),
    FOOD_STORAGE("food storage",0,Asset.WOOD,5,0, Texture.GROUND,50,
            200,0,0, 0, 0,0,0,0,
            0,null,null,2, Category.STORAGE, null),
    KEEP("keep",0,Asset.STONE,0, 0,Texture.GROUND,100,0,0,
            0, 8,0,0,20,10,35,
            null,null,2, Category.BUILDING, KeepMethods.getKeepMethods()),
    TREE("tree",0,Asset.STONE,0, 0,Texture.GROUND,100,0,0,
            0, 0,0,0,0,0,0,
            null,null,1, Category.BUILDING, KeepMethods.getKeepMethods());
    private final int workersNumber;
    private int hitpoint;
    private final Texture groundType;
    private final int goldCost;
    private final Asset resourceType;
    private final int resourceCost;
    private final String name;
    private final int height;
    private final int Capacity;
    private final int attackPoint;
    private final int popularityEffect;
    private final int populationEffect;
    private final int productionRate;
    private final Asset productionAsset;
    private final int producePrice;
    private final int fireRange;
    private final int defendRange;
    private final int soldiersCapacity;
    private final Asset outputProduct;
    private final int width;
    private final Category category;
    private final SelectBuildingMenuMethods selectBuildingMenuMethods;
    private static final ArrayList<BuildingType> barracks = new ArrayList<>();
    static {

    }
    BuildingType(String name, int goldCost,Asset resourceType, int resourceCost,int workersNumber,
                 Texture groundType, int height, int capacity, int attackPoint, int popularityEffect,
                 int populationEffect, int productionRate, int producePrice, int fireRange, int defendRange,
                 int soldiersCapacity, Asset productionAsset, Asset outputProduct, int width, Category category,
                 SelectBuildingMenuMethods selectBuildingMenuMethods) {
        this.workersNumber = workersNumber;
        this.groundType = groundType;
        this.resourceType = resourceType;
        this.resourceCost = resourceCost;
        this.productionAsset = productionAsset;
        this.goldCost = goldCost;
        this.name = name;
        this.height = height;
        Capacity = capacity;
        this.attackPoint = attackPoint;
        this.popularityEffect = popularityEffect;
        this.populationEffect = populationEffect;
        this.productionRate = productionRate;
        this.producePrice = producePrice;
        this.fireRange = fireRange;
        this.defendRange = defendRange;
        this.soldiersCapacity = soldiersCapacity;
        this.outputProduct = outputProduct;
        this.width = width;
        this.category = category;
        this.selectBuildingMenuMethods = selectBuildingMenuMethods;
    }

    public int getWorkersNumber() {
        return workersNumber;
    }
    public int getHitpoint() {
        return hitpoint;
    }

    public Texture getGroundType() {
        return groundType;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getCapacity() {
        return Capacity;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public int getPopularityEffect() {
        return popularityEffect;
    }

    public int getPopulationEffect() {
        return populationEffect;
    }

    public int getProductionRate() {
        return productionRate;
    }

    public int getProducePrice() {
        return producePrice;
    }

    public int getFireRange() {
        return fireRange;
    }

    public int getDefendRange() {
        return defendRange;
    }

    public int getSoldiersCapacity(){
        return soldiersCapacity;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public Asset getResourceType() {
        return resourceType;
    }

    public int getResourceCost() {
        return resourceCost;
    }

    public Asset getProductionAsset() {
        return productionAsset;
    }

    public Asset getOutputProduct() {
        return outputProduct;
    }

    public int getWidth() {
        return width;
    }

    public SelectBuildingMenuMethods getSelectBuildingMenuMethods() {
        return selectBuildingMenuMethods;
    }

    public BuildingType getBuildingTypeByName(String name) {
        for (BuildingType buildingType : BuildingType.values())
            if (buildingType.getName().equals(name))
                return buildingType;
        return null;
    }

    public static Asset getAnotherWeapon(BuildingType buildingType, Asset weapon) {
        for (BuildingType buildingType1 : BuildingType.values()) {
            if (buildingType1.getName().equals(buildingType1.getName()))
                if (!buildingType1.getProductionAsset().equals(weapon))
                    return buildingType1.getOutputProduct();
        }
        return null;
    }
}
