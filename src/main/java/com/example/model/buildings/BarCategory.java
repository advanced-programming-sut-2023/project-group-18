package com.example.model.buildings;

public enum BarCategory {
    CASTLE ("CastleBuilding"),
    FOOD ("FoodProcessing"),
    INDUSTRIAL ("IndustryBuilding"),
    TOWN ("TownBuilding"),
    WEAPON ("WeaponBuilding"),
    FARM ("FarmBuilding"),
    TOWER ("TowerBuilding"),
    NONE ("None");

    private final String name;

    private BarCategory(String name) {
        this.name = name;
    }

    public static BarCategory getBarCategoryByName(String name) {
        for (BarCategory barCategory : BarCategory.values())
            if (barCategory.getName().equals(name))
                return barCategory;
        return null;
    }

    public String getName() {
        return name;
    }

}
