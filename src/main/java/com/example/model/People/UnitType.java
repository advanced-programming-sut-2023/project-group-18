package com.example.model.People;

public enum UnitType {

    ;

    private final String name;
    private final int maxSpeed;
    private final int maxHitpoint;

    UnitType(String name, int maxSpeed, int maxHitpoint) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.maxHitpoint = maxHitpoint;
    }

    public static UnitType getUnitTypeByName(String name) {
        for (UnitType unitType : UnitType.values())
            if (unitType.name.equals(name)) return unitType;
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
