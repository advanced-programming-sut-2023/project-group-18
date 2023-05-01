package com.example.model.Buildings;

import java.util.HashMap;

public class BuildingType {
    private final int workersNumber;
    private final int maxHitpoint;
    private final String groundType;
    private final HashMap<String, Integer> costHashmap;
    private final String name;
    private final int workersNumner;
    private final int height;
    private final int Capacity;
    private final int attackPoint;
    private final int popularityEffect;
    private final int populationEffect;
    private final int prouctionRate;
    private final int producePrice;
    private int hitpoint;
    
    public BuildingType(int workersNumber, String groundType, HashMap<String, Integer> costHashmap, String name, int workersNumner, int height, int capacity, int attackPoint, int popularityEffect, int populationEffect, int prouctionRate, int producePrice) {
        this.workersNumber = workersNumber;
        this.groundType = groundType;
        this.costHashmap = costHashmap;
        this.name = name;
        this.workersNumner = workersNumner;
        this.height = height;
        Capacity = capacity;
        this.attackPoint = attackPoint;
        this.popularityEffect = popularityEffect;
        this.populationEffect = populationEffect;
        this.prouctionRate = prouctionRate;
        this.producePrice = producePrice;
    }

    public int getWorkersNumber() {
        return workersNumber;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public String getGroundType() {
        return groundType;
    }

    public HashMap<String, Integer> getCostHashmap() {
        return costHashmap;
    }

    public String getName() {
        return name;
    }

    public int getWorkersNumner() {
        return workersNumner;
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

    public int getProuctionRate() {
        return prouctionRate;
    }

    public int getProducePrice() {
        return producePrice;
    }
}
