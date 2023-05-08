package com.example.model.People;

import com.example.model.Buildings.BuildingType;

public enum UnitType {
    SOLDIER("Soldier", 0, 0,null),
    PEASANTS("Peasants", 0,1,null);
    ;

    private final String name;
    private final int maxSpeed;
    private final int maxHitpoint;
    private final BuildingType buildingType;

    UnitType(String name, int maxSpeed, int maxHitpoint, BuildingType buildingType) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.maxHitpoint = maxHitpoint;
        this.buildingType = buildingType;
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

}
