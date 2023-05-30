package com.example.model.buildings;

import com.example.model.assets.Asset;
import com.example.model.map.Texture;

public enum BuildingType {
    SMALL_STONE_GATEHOUSE("small stone gatehouse",0,Asset.STONE,0, 0,
            Texture.GROUND,100,0,0,0, 8,0, 0,
            20,10,35,null,null,2, Category.GATE,
            GateMethods.getGateMethods(), 500),
    BIG_STONE_GATEHOUSE("big stone gatehouse",0,Asset.STONE,20, 0,Texture.GROUND,
            1,1,1,1, 1,1, 1,1,
            1,1,null,null,3, Category.GATE,
            GateMethods.getGateMethods(), 1000),
    DRAWBRIDGE("Drawbridge",0,Asset.WOOD,10,0, Texture.POOL,0,0,
            0,0,0, 0,0,0,0,0,
            null,null,1,Category.BUILDING, null,1),
    LOOKOUT_TOWER("lookout tower",0,Asset.STONE,10,0,null,300,
            0,0,0,0,0,0,
            40,10,30,null,null,1,Category.TOWER,
            TowerMethods.getTowerMethods(),500),
    PERIMETER_TOWER("perimeter tower",0,Asset.STONE,10, 0,Texture.GROUND,
            250,0,0,0, 0,0,0,40,
            10,40,null,null,1, Category.TOWER,
            TowerMethods.getTowerMethods(),600),
    TURRET("turret",0,Asset.STONE,15,0, Texture.GROUND,200,0,
            0,0,0, 0,0, 50,20,
            40,null,null,2, Category.TOWER, TowerMethods.getTowerMethods(),
            700),
    SQUARE_TOWER("square tower",0,Asset.STONE,35,0, Texture.GROUND,200,
            0,0,0,0, 0,0,60,20,
            50,null,null,2, Category.TOWER, TowerMethods.getTowerMethods(),
            900),
    CIRCLE_TOWER("circle tower",0,Asset.STONE,40,0, Texture.GROUND,200,
            0,0,0,0, 0,0,60,20,
            100,null,null,2, Category.TOWER, TowerMethods.getTowerMethods(),
            1000),
    ARMOURY("armoury",0,Asset.WOOD,5,0,
            Texture.GROUND,50,50,0,0,0, 0,0,
            0,0,0,null,null,2, Category.STORAGE,
            StorageMethods.getStorageMethods(),200),
    BARRACKS("barracks",0,Asset.STONE,15,0, Texture.GROUND,50,0,
            0,0,0, 0,0,0,0,
            0,null,null,3, Category.BARRACKS
            ,BarracksMethods.getBarracksMethods(),200),
    MERCENARY_POST("mercenary post",0,Asset.WOOD,10,0,
            Texture.GROUND,50,0,0,0,0, 0,0,
            0,0,0,null,null,2, Category.BARRACKS,
            BarracksMethods.getBarracksMethods(),200),
    ENGINEER_GUILD("engineer guild",100,Asset.WOOD,10,0,
            Texture.GROUND,50,0,0,0,0, 0,0,
            0,0,0,null,null,2, Category.BARRACKS,
            BarracksMethods.getBarracksMethods(),150),
    KILLING_PIT("killing pit",0,Asset.WOOD,6,0,
            Texture.GROUND,0,0,0,0,0, 0,0,
            0,0,0,null,null,1, Category.BUILDING,
            null,1),
    INN("inn",100,Asset.WOOD,20,1, Texture.GROUND,60,0,0,
            4,0,5,2,0,0,0, Asset.ALE,
            null,2, Category.PROCESSING,null,200),
    MILL("mill",0,Asset.WOOD,20,3,
            Texture.GROUND,200,0,0,0,0, 5,5,
            0,0,0,Asset.WHEAT,Asset.FLOUR,1, Category.PROCESSING
            ,null,300),
    IRON_MINE("iron mine",0,Asset.WOOD,20,2, Texture.IRON,10,0,
            0,0,0, 5,0,0,0,0,
            null,Asset.IRON,1,Category.INDUSTRIAL_BUILDING,null,200),
    MARKET("market",0,Asset.WOOD,5,1, Texture.GROUND,30,0,
            0,0,0, 0,0,0,0,0,
            null,null,2, Category.BUILDING, MarketMethods.getMarketMethods(),200),
    OX_TETHER("ox tether",0,Asset.WOOD,5,1, Texture.GROUND,5,0,
            0,0,0, 0,0, 0,0,
            0,null,null,1, Category.INDUSTRIAL_BUILDING
            , null,200),
    PITCH_RIG("pitch rig",0,Asset.WOOD,20,1, Texture.PLAIN,20,0,
            0,0,0, 5,0,0,0,0,
            null,null,1, Category.BUILDING, null,200),
    QUARRY("quarry",0,Asset.WOOD,20,3, Texture.STONE, 1,30,
            0,0,0, 10,0,0,0,
            0,null,Asset.STONE,2, Category.INDUSTRIAL_BUILDING
            , null,400),
    STOCKPILE("stockpile",0,null,0,0, Texture.GROUND,50,
            100,0,0,0, 0,0,0,0,
            0,null,null,2,Category.STORAGE
            , StorageMethods.getStorageMethods(),50),
    WOOD_CUTTER("wood cutter",0,Asset.WOOD,3,1, Texture.GROUND,20,0,
            0,0,0, 10,0,0,0,
            0,null,Asset.WOOD,1, Category.INDUSTRIAL_BUILDING
            , null,50),
    HOVEL("hovel",0,Asset.WOOD,6,0, Texture.GROUND,50,0,0,
            0,6, 0,0,0,0,0,
            null,null,2,Category.BUILDING, null,100),
    CHURCH("church",250,null,0,0, Texture.GROUND,100,0,
            0,2,0, 0,0,0,0,
            0,null,null,3,Category.BUILDING, null
            ,200),
    CATHEDRAL("cathedral",1000,null,0,0, Texture.GROUND,120,
            0,0,2,0, 0,0,0,0,
            0,null,null,4,Category.BARRACKS
            , BarracksMethods.getBarracksMethods(),400),
    ARMOURER("armourer",100,Asset.WOOD,20,1, Texture.GROUND,50,0,
            0,0,0, 2,2,0,0,0,
            Asset.IRON, Asset.METAL_ARMOR,2, Category.GUNSMITH,
            null,200),
    BLACKSMITH_MACE("blacksmith",100,Asset.WOOD,20,1,
            Texture.GROUND,50,0,0,0,0, 2,2,
            0,0,0,Asset.IRON,Asset.MACE,2, Category.GUNSMITH,
            GunsmithMethods.getGunsmithMethods(),200),
    BLACKSMITH_SWORD("blacksmith",100,Asset.WOOD,20,1,
            Texture.GROUND,50,0,0,0,0, 2,2,
            0,0,0,Asset.IRON,Asset.SWORDS,2, Category.GUNSMITH,
            GunsmithMethods.getGunsmithMethods(),200),
    FLETCHER("fletcher",100,Asset.WOOD,20,1, Texture.GROUND,50,0,
            0,0,0, 2,2,0,0,
            0,Asset.IRON,Asset.MACE,2, Category.GUNSMITH, GunsmithMethods.getGunsmithMethods()
            ,200),
    POLETURNER("poleturner",100,Asset.WOOD,20,1,
            Texture.GROUND,50,0,0,0,0, 2,2,
            0,0,0,Asset.IRON,Asset.MACE,2, Category.GUNSMITH,
            GunsmithMethods.getGunsmithMethods(),200),
    OIL_SMELTER("oil smelter",100,Asset.IRON,10,1, Texture.PETROLEUM,20,
            0,0,0,0, 5,0,0,0,
            0,null,Asset.PITCH,1, Category.INDUSTRIAL_BUILDING
            , null,200),
    PITCH_DITCH("pitch ditch",0,Asset.PITCH,2,0, Texture.GROUND,0,0,
            0,0,0, 0,0,0,0,0,
            null,null,1, Category.BUILDING, null,200),
    CAGED_WAR_DOGS("caged war dogs",100,Asset.WOOD,10,0, Texture.GROUND,50,
            0,0,0,0, 0,0,0,0,
            0,null,null,2, Category.BUILDING,null
            ,200),
    SIEGE_TENT("siege tent",0,null,0, 1,Texture.GROUND,50,
            0,0,0, 0,0, 0,0,0,
            0,null,null,1,Category.BUILDING, null,
            200),
    STABLE("stable",400,Asset.WOOD,20,0, Texture.GROUND,50,0,
            0,0,0, 0,0,0,0,
            0,null,null,3,Category.STABLE, null,
            200),
    APPLE_FARM("apple farm",0,Asset.WOOD,5,1, Texture.OVERGROWN_GRASSLAND,
            10,0,0,0,0, 5,0,0,
            0,0,null,Asset.APPLE,4,Category.FARM,
            null,200),
    DAIRY_PRODUCTS("dairy products",0,Asset.WOOD,10,1, Texture.OVERGROWN_GRASSLAND,
            10,0,0,0,0, 5,0,0,
            0,0,null, Asset.CHEESE,4,Category.DAIRY_PRODUCTS,
            null,200),
    HOP_FARM("hop farm",0,Asset.WOOD,15,1, Texture.OVERGROWN_GRASSLAND,10,
            0,0,0,0, 5,0,0,0,
            0,null,Asset.HOPS,4,Category.FARM, null,200),
    HUNT_POST("hunt post",0,Asset.WOOD,10,1, Texture.GROUND,30,0,
            0,0,0, 5,0,0,0,0,
            null,Asset.MEAT,1,Category.FARM, null,200),
    WHEAT_FARM("wheat farm",0,Asset.WOOD,10,1, Texture.OVERGROWN_GRASSLAND,
            10,0,0,0, 0,5,0,0,
            0,0,null,Asset.WHEAT,4,Category.FARM, null,
            200),
    BAKERY("bakery",0, Asset.WOOD,10,1, Texture.GROUND,50,0,
            0,0,0, 5,1,0,0,0,
            Asset.FLOUR,Asset.BREAD,2, Category.PROCESSING, null,200),
    BREWERY("brewery",0,Asset.WOOD,10,1, Texture.GROUND,50,0,
            0,0,0, 1,1,0,0,
            0,Asset.HOPS,Asset.ALE,2,Category.PROCESSING, null,200),
    FOOD_STORAGE("food storage",0,Asset.WOOD,5,0, Texture.GROUND,50,
            200,0,0, 0, 0,0,0,0,
            0,null,null,2, Category.STORAGE, null
            ,100),
    KEEP("keep",0,Asset.STONE,0, 0,Texture.GROUND,100,0,0,
            0, 8,0,0,20,10,35,
            null,null,2, Category.BUILDING, KeepMethods.getKeepMethods(),
            1000000),
    TREE("tree",0,null,0, 0,Texture.OVERGROWN_GRASSLAND,100,0,
            0, 0, 0,0,0,0,0
            ,0, null,null,1, Category.BUILDING, null
            ,1),
    WALL("wall",0,Asset.STONE,1, 0,Texture.GROUND,100,0,0,
            0, 0,0,0,20,10,4,
            null,null,1, Category.WALL, null,30),
    STAIR("stair",0,Asset.STONE,5, 0,Texture.GROUND,100,0,0,
            0, 0,0,0,20,10,10,
            null,null,1, Category.STAIR, null,30)
    ;
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

    BuildingType(String name, int goldCost,Asset resourceType, int resourceCost,int workersNumber,
                 Texture groundType, int height, int capacity, int attackPoint, int popularityEffect,
                 int populationEffect, int productionRate, int producePrice, int fireRange, int defendRange,
                 int soldiersCapacity, Asset productionAsset, Asset outputProduct, int width, Category category,
                 SelectBuildingMenuMethods selectBuildingMenuMethods, int hitpoint) {
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
        this.hitpoint = hitpoint;
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

    public Category getCategory() {
        return category;
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
