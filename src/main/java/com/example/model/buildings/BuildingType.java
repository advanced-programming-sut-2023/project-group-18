package com.example.model.buildings;

import com.example.model.assets.Asset;
import com.example.view.Main;
import com.example.view.images.TextureImages;

import javafx.scene.image.Image;

public enum BuildingType {
    SMALL_STONE_GATEHOUSE("small stone gatehouse",0,Asset.STONE,0, 0,
            TextureImages.GROUND,100,0,0,0, 8,0, 0,
            20,10,35,null,null,2, Category.GATE,
            500, BarCategory.TOWER, 40, 70, 20, 50),
    BIG_STONE_GATEHOUSE("big stone gatehouse",0,Asset.STONE,20, 0,TextureImages.GROUND,
            1,1,1,1, 1,1, 1,1,
            1,1,null,null,3, Category.GATE,
            1000, BarCategory.TOWER, 35, 70, 15, 50),
    DRAWBRIDGE("Drawbridge",0,Asset.WOOD,10,0, TextureImages.WATER,0,0,
            0,0,0, 0,0,0,0,0,
            null,null,1,Category.BUILDING, 1, BarCategory.TOWN, 30, 75, 15, 55),
    LOOKOUT_TOWER("lookout tower",0,Asset.STONE,10,0,null,300,
            0,0,0,0,0,0,
            40,10,30,null,null,1,Category.TOWER,
            500, BarCategory.TOWER, 80, 140, 42, 130),
    PERIMETER_TOWER("perimeter tower",0,Asset.STONE,10, 0,TextureImages.GROUND,
            250,0,0,0, 0,0,0,40,
            10,40,null,null,1, Category.TOWER,
            600, BarCategory.TOWER, 50, 90, 25, 75),
    TURRET("turret",0,Asset.STONE,15,0, TextureImages.GROUND,200,0,
            0,0,0, 0,0, 50,20,
            40,null,null,2, Category.TOWER,
            700, BarCategory.TOWER, 50, 90, 23, 75),
    SQUARE_TOWER("square tower",0,Asset.STONE,35,0, TextureImages.GROUND,200,
            0,0,0,0, 0,0,60,20,
            50,null,null,2, Category.TOWER,
            900,BarCategory.TOWER, 50, 90, 25, 75),
    CIRCLE_TOWER("circle tower",0,Asset.STONE,40,0, TextureImages.GROUND,200,
            0,0,0,0, 0,0,60,20,
            100,null,null,2, Category.TOWER,
            1000, BarCategory.TOWER, 50, 90, 25, 75),
    ARMOURY("armoury",0,Asset.WOOD,5,0,
            TextureImages.GROUND,50,50,0,0,0, 0,0,
            0,0,0,null,null,2, Category.STORAGE,
            200,BarCategory.CASTLE, 50, 70, 25, 45),
    BARRACKS("barracks",0,Asset.STONE,15,0, TextureImages.GROUND,50,0,
            0,0,0, 0,0,0,0,
            0,null,null,3, Category.BARRACKS
            ,200,BarCategory.CASTLE, 30, 60, 15, 45),
    MERCENARY_POST("mercenary post",0,Asset.WOOD,10,0,
            TextureImages.GROUND,50,0,0,0,0, 0,0,
            0,0,0,null,null,2, Category.BARRACKS,
            200,BarCategory.CASTLE, 30, 50, 15, 30),
    ENGINEER_GUILD("engineer guild",100,Asset.WOOD,10,0,
            TextureImages.GROUND,50,0,0,0,0, 0,0,
            0,0,0,null,null,2, Category.BARRACKS,
            150,BarCategory.CASTLE, 30, 60, 15, 42),
//     KILLING_PIT("killing pit",0,Asset.WOOD,6,0,
//             TextureImages.GROUND,0,0,0,0,0, 0,0,
//             0,0,0,null,null,1, Category.BUILDING,
//             1, BarCategory.CASTLE, 30, 40, 0, 0),
    INN("inn",100,Asset.WOOD,20,1, TextureImages.GROUND,60,0,0,
            4,0,5,2,0,0,0, Asset.ALE,
            null,2, Category.PROCESSING,200,BarCategory.FOOD, 30, 60, 15, 45),
    MILL("mill",0,Asset.WOOD,20,3,
            TextureImages.GROUND,200,0,0,0,0, 5,5,
            0,0,0,Asset.WHEAT,Asset.FLOUR,1, Category.PROCESSING
            ,300, BarCategory.FOOD, 60, 120, 32, 105),
    IRON_MINE("iron mine",0,Asset.WOOD,20,2, TextureImages.IRON,10,0,
            0,0,0, 5,0,0,0,0,
            null,Asset.IRON,1,Category.INDUSTRIAL_BUILDING,200, BarCategory.INDUSTRIAL, 30, 75, 15, 50),
    MARKET("market",0,Asset.WOOD,5,1, TextureImages.GROUND,30,0,
            0,0,0, 0,0,0,0,0,
            null,null,2, Category.BUILDING,200, BarCategory.INDUSTRIAL, 30, 75, 15, 50),
    OX_TETHER("ox tether",0,Asset.WOOD,5,1, TextureImages.GROUND,5,0,
            0,0,0, 0,0, 0,0,
            0,null,null,1, Category.INDUSTRIAL_BUILDING
            , 200, BarCategory.INDUSTRIAL, 30, 75, 15, 50),
    PITCH_RIG("pitch rig",0,Asset.WOOD,20,1, TextureImages.GROUND,20,0,
            0,0,0, 5,0,0,0,0,
            null,null,1, Category.BUILDING, 200, BarCategory.INDUSTRIAL, 30, 75, 15, 40),
    QUARRY("quarry",0,Asset.WOOD,20,3, TextureImages.SLAB, 1,30,
            0,0,0, 10,0,0,0,
            0,null,Asset.STONE,2, Category.INDUSTRIAL_BUILDING
            , 400, BarCategory.INDUSTRIAL, 30, 75, 15, 60),
    STOCKPILE("stockpile",0,null,0,0, TextureImages.GROUND,50,
            100,0,0,0, 0,0,0,0,
            0,null,null,2,Category.STORAGE
            , 50, BarCategory.INDUSTRIAL, 30, 30, 15, 15),
    WOOD_CUTTER("wood cutter",0,Asset.WOOD,3,1, TextureImages.GROUND,20,0,
            0,0,0, 10,0,0,0,
            0,null,Asset.WOOD,1, Category.INDUSTRIAL_BUILDING
            , 50, BarCategory.INDUSTRIAL, 30, 60, 15, 40),
    HOVEL("hovel",0,Asset.WOOD,6,0, TextureImages.GROUND,50,0,0,
            0,6, 0,0,0,0,0,
            null,null,2,Category.BUILDING, 100, BarCategory.TOWN, 30, 60, 15, 40),
    CHURCH("church",250,null,0,0, TextureImages.GROUND,100,0,
            0,2,0, 0,0,0,0,
            0,null,null,3,Category.BUILDING
            ,200, BarCategory.TOWN, 30, 75, 15, 50),
    CATHEDRAL("cathedral",1000,null,0,0, TextureImages.GROUND,120,
            0,0,2,0, 0,0,0,0,
            0,null,null,4,Category.BARRACKS
            , 400, BarCategory.TOWN, 60, 90, 30, 50),
    ARMOURER("armourer",100,Asset.WOOD,20,1, TextureImages.GROUND,50,0,
            0,0,0, 2,2,0,0,0,
            Asset.IRON, Asset.METAL_ARMOR,2, Category.GUNSMITH,
            200, BarCategory.WEAPON, 30, 60, 15, 45),
    BLACKSMITH_MACE("blacksmith",100,Asset.WOOD,20,1,
            TextureImages.GROUND,50,0,0,0,0, 2,2,
            0,0,0,Asset.IRON,Asset.MACE,2, Category.GUNSMITH,
            200, BarCategory.WEAPON, 30, 60, 15, 45),
    BLACKSMITH_SWORD("blacksmith",100,Asset.WOOD,20,1,
            TextureImages.GROUND,50,0,0,0,0, 2,2,
            0,0,0,Asset.IRON,Asset.SWORDS,2, Category.GUNSMITH,
            200, BarCategory.NONE, 30, 40, 0, 0),
    FLETCHER("fletcher",100,Asset.WOOD,20,1, TextureImages.GROUND,50,0,
            0,0,0, 2,2,0,0,
            0,Asset.IRON,Asset.MACE,2, Category.GUNSMITH
            ,200, BarCategory.WEAPON, 30, 60, 15, 45),
    POLETURNER("poleturner",100,Asset.WOOD,20,1,
            TextureImages.GROUND,50,0,0,0,0, 2,2,
            0,0,0,Asset.IRON,Asset.MACE,2, Category.GUNSMITH,
            200,BarCategory.WEAPON, 30, 60, 15, 45),
    OIL_SMELTER("oil smelter",100,Asset.IRON,10,1, TextureImages.GROUND,20,
            0,0,0,0, 5,0,0,0,
            0,null,Asset.PITCH,1, Category.INDUSTRIAL_BUILDING
            ,200, BarCategory.INDUSTRIAL, 30, 60, 15, 40),
    PITCH_DITCH("pitch ditch",0,Asset.PITCH,2,0, TextureImages.GROUND,0,0,
            0,0,0, 0,0,0,0,0,
            null,null,1, Category.BUILDING, 200, BarCategory.INDUSTRIAL, 30, 75, 15, 40),
    CAGED_WAR_DOGS("caged war dogs",100,Asset.WOOD,10,0, TextureImages.GROUND,50,
            0,0,0,0, 0,0,0,0,
            0,null,null,2, Category.BUILDING
            ,200, BarCategory.INDUSTRIAL, 30, 75, 15, 40),
    SIEGE_TENT("siege tent",0,null,0, 1,TextureImages.GROUND,50,
            0,0,0, 0,0, 0,0,0,
            0,null,null,1,Category.BUILDING,
            200, BarCategory.NONE, 30, 40, 0, 0),
    STABLE("stable",400,Asset.WOOD,20,0, TextureImages.GROUND,50,0,
            0,0,0, 0,0,0,0,
            0,null,null,3,Category.STABLE,
            200, BarCategory.CASTLE, 30, 50, 15, 30),
    APPLE_FARM("apple farm",0,Asset.WOOD,5,1, TextureImages.FARM,
            10,0,0,0,0, 5,0,0,
            0,0,null,Asset.APPLE,4,Category.FARM,
            200, BarCategory.FARM, 40, 60, 20, 40),
    DAIRY_PRODUCTS("dairy products",0,Asset.WOOD,10,1, TextureImages.FARM,
            10,0,0,0,0, 5,0,0,
            0,0,null, Asset.CHEESE,4,Category.DAIRY_PRODUCTS,
            200, BarCategory.FARM, 40, 60, 20, 40),
    HOP_FARM("hop farm",0,Asset.WOOD,15,1, TextureImages.FARM,10,
            0,0,0,0, 5,0,0,0,
            0,null,Asset.HOPS,4,Category.FARM, 200, BarCategory.FARM, 40, 60, 20, 40),
    HUNT_POST("hunt post",0,Asset.WOOD,10,1, TextureImages.GROUND,30,0,
            0,0,0, 5,0,0,0,0,
            null,Asset.MEAT,1,Category.FARM, 200, BarCategory.FARM, 40, 60, 20, 40),
    WHEAT_FARM("wheat farm",0,Asset.WOOD,10,1, TextureImages.FARM,
            10,0,0,0, 0,5,0,0,
            0,0,null,Asset.WHEAT,4,Category.FARM,
            200, BarCategory.FOOD, 40, 60, 20, 40),
    BAKERY("bakery",0, Asset.WOOD,10,1, TextureImages.GROUND,50,0,
            0,0,0, 5,1,0,0,0,
            Asset.FLOUR,Asset.BREAD,2, Category.PROCESSING, 200, BarCategory.FOOD, 40, 60, 20, 45),
    BREWERY("brewery",0,Asset.WOOD,10,1, TextureImages.GROUND,50,0,
            0,0,0, 1,1,0,0,
            0,Asset.HOPS,Asset.ALE,2,Category.PROCESSING, 200, BarCategory.FOOD, 40, 75, 20, 60),
    FOOD_STORAGE("food storage",0,Asset.WOOD,5,0, TextureImages.GROUND,50,
            200,0,0, 0, 0,0,0,0,
            0,null,null,2, Category.STORAGE
            ,100, BarCategory.FOOD, 40, 75, 20, 60),
    KEEP("keep",0,Asset.STONE,0, 0,TextureImages.GROUND,100,0,0,
            0, 8,0,0,20,10,35,
            null,null,2, Category.BUILDING,
            1000000, BarCategory.NONE, 40, 60, 20, 45),
    WALL("wall",0,Asset.STONE,1, 0,TextureImages.GROUND,100,0,0,
            0, 0,0,0,20,10,4,
            null,null,1, Category.WALL, 30, BarCategory.TOWER, 30, 40, 0, 0),
    STAIR("stair",0,Asset.STONE,5, 0,TextureImages.GROUND,100,0,0,
            0, 0,0,0,20,10,10,
            null,null,1, Category.STAIR, 30, BarCategory.TOWER, 30, 40, 0, 0),
    FIRE("fire",0,Asset.STONE,5, 0,TextureImages.GROUND,100,0,0,
            0, 0,0,0,20,10,10,
            null,null,1, Category.BUILDING, 30, BarCategory.NONE, 40, 40, 20, 20)
    ;
    private final Image image;
    private final int workersNumber;
    private int hitpoint;
    private final TextureImages groundType;
    private final int goldCost;
    private final Asset resourceType;
    private final int resourceCost;
    private final String name;
    private final int height;
    private final int capacity;
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
    private final BarCategory barCategory;
    private final double imageWidth;
    private final double imageHeight;
    private final double resetX;
    private final double resetY;

    BuildingType(String name, int goldCost,Asset resourceType, int resourceCost,int workersNumber,
                 TextureImages groundType, int height, int capacity, int attackPoint, int popularityEffect,
                 int populationEffect, int productionRate, int producePrice, int fireRange, int defendRange,
                 int soldiersCapacity, Asset productionAsset, Asset outputProduct, int width, Category category
                 , int hitpoint, BarCategory barCategory, double imageWidth, double imageHeight, double resetX, double resetY) {
        this.workersNumber = workersNumber;
        this.groundType = groundType;
        this.resourceType = resourceType;
        this.resourceCost = resourceCost;
        this.productionAsset = productionAsset;
        this.goldCost = goldCost;
        this.name = name;
        this.height = height;
        this.capacity = capacity;
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
        this.hitpoint = hitpoint;
        this.barCategory = barCategory;
        this.image = new Image(Main.class.getResource("/images/buildings/" + name + ".png").toExternalForm());
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.resetX = resetX;
        this.resetY = resetY;
    }

    public Image getImage() {
        return image;
    }

    public int getWorkersNumber() {
        return workersNumber;
    }
    public int getHitpoint() {
        return hitpoint;
    }

    public TextureImages getGroundType() {
        return groundType;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getCapacity() {
        return capacity;
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

    public static BuildingType getBuildingTypeByName(String name) {
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

    public BarCategory getBarCategory() {
        return barCategory;
    }

    public double getImageHeight() {
        return imageHeight;
    }

    public double getImageWidth() {
        return imageWidth;
    }

    public double getResetX() {
        return resetX;
    }

    public double getResetY() {
        return resetY;
    }

}
